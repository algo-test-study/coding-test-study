/**
 * @param {number[][]} image
 * @param {number} sr
 * @param {number} sc
 * @param {number} color
 * @return {number[][]}
 */
var floodFill = function (image, sr, sc, color) {
  const dist = [[-1, 0], [1, 0], [0, -1], [0, 1]];

  const originColor = image[sr][sc];
  if (originColor === color) return image;
  let q = [[sr, sc]];
  image[sr][sc] = color;
  while (q.length > 0) {
    const loc = q.shift();
    const [x, y] = loc;

    for (let i = 0; i < dist.length; i++) {
      const rx = x + dist[i][0];
      const ry = y + dist[i][1];

      if (rx < 0 || ry < 0 || rx >= image.length || ry >= image[0].length) continue;

      if (image[rx][ry] == originColor) {
        image[rx][ry] = color;
        q.push([rx, ry]);
      }
    }
  }

  return image;
};