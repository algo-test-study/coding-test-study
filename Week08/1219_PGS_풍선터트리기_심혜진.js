function solution(a) {
  const n=a.length;
  if (n<=2) return n;

  const leftMin=new Array(n);
  const rightMin=new Array(n);

  let mn=Infinity;
  for (let i=0;i<n;i++) {
    mn=Math.min(mn,a[i]);
    leftMin[i]=mn;
  }

  mn=Infinity;
  for (let i=n-1;i>=0;i--) {
    mn = Math.min(mn,a[i]);
    rightMin[i]=mn;
  }

  let count=0;

  for (let i=0;i<n;i++) {
    const leftSideMin = i===0 ? Infinity:leftMin[i-1];
    const rightSideMin = i===n-1 ? Infinity:rightMin[i+1];

    if (a[i]<=leftSideMin||a[i]<=rightSideMin) {
      count++;
    }
  }

  return count;
}
