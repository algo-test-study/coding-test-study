var mergeKLists = function (lists) {
  if (!lists || lists.length === 0) return null;

  class MinHeap {
    constructor() { this.a = []; }
    size() { return this.a.length; }
    push(node) {
      const a = this.a;
      a.push(node);
      let i = a.length - 1;
      while (i > 0) {
        const p = (i - 1) >> 1;
        if (a[p].val <= a[i].val) break;
        [a[p], a[i]] = [a[i], a[p]];
        i = p;
      }
    }
    pop() {
      const a = this.a;
      if (a.length === 0) return null;
      const top = a[0];
      const last = a.pop();
      if (a.length > 0) {
        a[0] = last;
        let i = 0;
        while (true) {
          let l = i * 2 + 1, r = l + 1, m = i;
          if (l < a.length && a[l].val < a[m].val) m = l;
          if (r < a.length && a[r].val < a[m].val) m = r;
          if (m === i) break;
          [a[i], a[m]] = [a[m], a[i]];
          i = m;
        }
      }
      return top;
    }
  }

  const heap = new MinHeap();
  for (let i = 0; i < lists.length; i++) if (lists[i]) heap.push(lists[i]);

  let head = null;
  let tail = null;

  while (heap.size() > 0) {
    const node = heap.pop();
    if (node.next) heap.push(node.next);

    node.next = null;

    if (head === null) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      tail = node;
    }
  }

  return head;
};