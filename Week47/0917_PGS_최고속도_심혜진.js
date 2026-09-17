// solution.js

function solution(city, road) {
  const n = city.length;
  const m = road.length;

  // 1. 좌표 -> 인덱스 매핑
  const coordToIndex = new Map();
  const nodes = []; // {x, y, type: 'city' | 'camera' | 'plain'}
  const cityIndex = new Array(n); // 도시 i번의 node index

  const key = (x, y) => `${x},${y}`;

  function addNode(x, y, type = 'plain') {
    const k = key(x, y);
    if (coordToIndex.has(k)) {
      const idx = coordToIndex.get(k);
      // city가 겹칠 수 없지만 혹시 type 겹치면 city 우선
      if (type === 'city') nodes[idx].type = 'city';
      if (type === 'camera' && nodes[idx].type !== 'city') nodes[idx].type = 'camera';
      return idx;
    }
    const idx = nodes.length;
    coordToIndex.set(k, idx);
    nodes.push({ x, y, type });
    return idx;
  }

  // 도시 노드 추가
  for (let i = 0; i < n; i++) {
    const [x, y] = city[i];
    const idx = addNode(x, y, 'city');
    cityIndex[i] = idx;
  }

  // 도로의 양 끝 점과 카메라(중점)를 우선 등록
  const roads = []; // { x1,y1,x2,y2, limit, camIdx, end1Idx, end2Idx }
  for (let i = 0; i < m; i++) {
    let [x1, y1, x2, y2, limit] = road[i];
    // 보장: x1 <= x2, y1 <= y2, 수평 또는 수직
    const end1Idx = addNode(x1, y1, 'plain');
    const end2Idx = addNode(x2, y2, 'plain');
    const midX = (x1 + x2) / 2;
    const midY = (y1 + y2) / 2;
    const camIdx = addNode(midX, midY, 'camera');
    roads.push({ x1, y1, x2, y2, limit, camIdx, end1Idx, end2Idx });
  }

  const totalNodesBeforeIntersections = nodes.length;

  // 2. 도로 교차점 계산 (수평-수직 쌍만 보면 됨)
  function isHorizontal(r) {
    return r.y1 === r.y2;
  }
  function isVertical(r) {
    return r.x1 === r.x2;
  }

  for (let i = 0; i < m; i++) {
    const r1 = roads[i];
    for (let j = i + 1; j < m; j++) {
      const r2 = roads[j];
      // 수평-수직만 교차 가능
      let h, v;
      if (isHorizontal(r1) && isVertical(r2)) {
        h = r1;
        v = r2;
      } else if (isHorizontal(r2) && isVertical(r1)) {
        h = r2;
        v = r1;
      } else {
        continue;
      }
      const y = h.y1;
      const x = v.x1;
      // 범위 내에 있는지 확인
      if (x >= h.x1 && x <= h.x2 && y >= v.y1 && y <= v.y2) {
        addNode(x, y, 'plain');
      }
    }
  }

  const N = nodes.length;

  // 3. 각 도로 위의 노드들 정렬해서 간선 구성
  const edges = []; // 전체 그래프용 간선 {u, v, limit}
  const adjNoCamera = Array.from({ length: N }, () => []); // 카메라 제외 그래프

  // 좌표가 이 도로 위에 있는지 판정
  function onRoad(r, nd) {
    if (r.x1 === r.x2) {
      // vertical: x 고정, y in [y1, y2]
      if (nd.x !== r.x1) return false;
      return nd.y >= r.y1 && nd.y <= r.y2;
    } else {
      // horizontal: y 고정, x in [x1, x2]
      if (nd.y !== r.y1) return false;
      return nd.x >= r.x1 && nd.x <= r.x2;
    }
  }

  for (let ri = 0; ri < m; ri++) {
    const r = roads[ri];
    const indicesOnRoad = [];

    for (let i = 0; i < N; i++) {
      const nd = nodes[i];
      if (onRoad(r, nd)) {
        indicesOnRoad.push(i);
      }
    }

    // 도로 방향에 따라 정렬
    if (r.x1 === r.x2) {
      // vertical: sort by y
      indicesOnRoad.sort((a, b) => nodes[a].y - nodes[b].y);
    } else {
      // horizontal: sort by x
      indicesOnRoad.sort((a, b) => nodes[a].x - nodes[b].x);
    }

    // 인접한 노드끼리 간선 생성
    for (let k = 0; k + 1 < indicesOnRoad.length; k++) {
      const u = indicesOnRoad[k];
      const v = indicesOnRoad[k + 1];
      const limit = r.limit;
      edges.push({ u, v, limit });

      // "카메라 없는 그래프": u,v 둘 다 카메라가 아닐 때만 추가
      if (nodes[u].type !== 'camera' && nodes[v].type !== 'camera') {
        adjNoCamera[u].push(v);
        adjNoCamera[v].push(u);
      }
    }
  }

  // 4. 카메라 없는 그래프에서 1번 도시(인덱스 cityIndex[0]) 기준 BFS
  const start = cityIndex[0];
  const visitedNoCamera = new Array(N).fill(false);
  const q = [start];
  visitedNoCamera[start] = true;
  while (q.length) {
    const cur = q.shift();
    for (const nxt of adjNoCamera[cur]) {
      if (!visitedNoCamera[nxt]) {
        visitedNoCamera[nxt] = true;
        q.push(nxt);
      }
    }
  }

  const INF = 1e15;
  const result = new Array(n - 1).fill(-1); // 도시 2..n

  // 5. 카메라 없이 도달 가능한 도시는 0 할당
  for (let i = 1; i < n; i++) {
    const idx = cityIndex[i];
    if (visitedNoCamera[idx]) {
      result[i - 1] = 0;
    }
  }

  // 남은 도시들만 대상으로 maximin path (카메라 있는 그래프)
  const need = [];
  for (let i = 1; i < n; i++) {
    if (result[i - 1] === -1) {
      need.push(i); // 도시 인덱스 (1-based)
    }
  }
  if (need.length === 0) return result;

  // Union-Find 준비
  const parent = new Array(N);
  const rank = new Array(N).fill(0);
  for (let i = 0; i < N; i++) parent[i] = i;

  function find(x) {
    while (parent[x] !== x) {
      parent[x] = parent[parent[x]];
      x = parent[x];
    }
    return x;
  }
  function union(a, b) {
    a = find(a);
    b = find(b);
    if (a === b) return false;
    if (rank[a] < rank[b]) {
      parent[a] = b;
    } else if (rank[a] > rank[b]) {
      parent[b] = a;
    } else {
      parent[b] = a;
      rank[a]++;
    }
    return true;
  }

  // 간선을 limit 내림차순으로 정렬
  edges.sort((a, b) => b.limit - a.limit);

  const neededSet = new Set(need); // 도시 인덱스(1..n-1)
  const cityDone = new Set();

  for (const e of edges) {
    union(e.u, e.v);

    // 1번 도시와 각 city 사이가 연결됐는지 체크
    const rootStart = find(start);
    for (const ci of neededSet) {
      if (!cityDone.has(ci)) {
        const cIdx = cityIndex[ci];
        if (find(cIdx) === rootStart) {
          // 이 시점의 e.limit 이 해당 도시의 maximin 값
          result[ci - 1] = e.limit;
          cityDone.add(ci);
        }
      }
    }
    if (cityDone.size === neededSet.size) break;
  }

  return result;
}

module.exports = solution;
