class MinHeap {
  constructor() {
      this.items = []
  }

  size() {
      return this.items.length;
  }

  insert(item) {
      this.items.push(item);
      this.bubbleUp();
  }

  pop() {
      if(this.size() === 0) return null;

      const min = this.items[0];

      this.items[0] = this.items[this.size()-1];

      this.items.pop();
      this.bubbleDown();
      return min;
  }

  bubbleUp() {
      let index = this.size()-1;
      while(index > 0){
          let parentIndex = Math.floor( ( index-1 ) /2);

          if(this.items[parentIndex][0] <= this.items[index][0]) break;

          this.swap(parentIndex, index)
          index = parentIndex;
      }
  }

  bubbleDown() {
      let index = 0;

      while( ( index * 2 ) + 1  < this.size() ){
          const leftChild = ( index * 2 )+1;
          const rightChild = ( index * 2 )+2;

          let smallerChild = rightChild < this.size() &&
              this.items[rightChild][0] < this.items[leftChild][0] ? rightChild : leftChild;

          if( this.items[smallerChild][0] > this.items[index][0] ) break;

          this.swap(index, smallerChild);

          index = smallerChild;
      }
  }

  swap(index, smallerChild) {
      [this.items[index], this.items[smallerChild]] = [this.items[smallerChild], this.items[index]]
  }
}


function solution(N, road, K){
  let distances = Array(N+1).fill(Infinity);

  distances[1] = 0

  let graph = Array.from({length:N+1}, ()=>[]);

  for( const [a,b,cost] of road) {
      graph[a].push([b,cost]);
      graph[b].push([a,cost]);
  }

  const heap = new MinHeap();
  heap.insert([0,1]);
  while(heap.size() > 0){
      const [dist, node] = heap.pop();

      for(const [nextNode, nextDist] of graph[node]) {
          const cost = dist + nextDist;
          if(cost < distances[nextNode]) {
              distances[nextNode] = cost;
              heap.insert([cost, nextNode]);
          }
      }
  }

  return distances.filter((dist)=> dist <= K).length;
}
