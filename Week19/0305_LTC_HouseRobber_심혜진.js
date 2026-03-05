var rob = function(nums) {
  const n = nums.length;
  if (n === 0) return 0;
  if (n === 1) return nums[0];

  let includePrev = nums[0];                 
  let excludePrev = Math.max(nums[0], nums[1]); 

  for (let i = 2; i < n; i++) {
    const current = Math.max(includePrev + nums[i], excludePrev);
    includePrev = excludePrev;
    excludePrev = current;
  }

  return excludePrev;
};
