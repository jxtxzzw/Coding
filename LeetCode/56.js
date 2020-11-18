
/**
 * @param {number[][]} intervals
 * @return {number[][]}
 */
var merge = function(intervals) {
    intervals.sort((a, b) => {
        if (a[0] == b[0]) {
            return a[1] - b[1];
        } else {
            return a[0] - b[0];
        }
    });
    let ans = [];
    let prev = intervals[0];
    for (const x of intervals) {
        if ((prev[1] >= x[0] && prev[1] <= x[1]) || (x[1] >= prev[0] && x[1] <= prev[1])) {
            prev[1] = Math.max(x[1], prev[1]);
        } else {
            ans.push([prev[0], prev[1]]);
            prev = x;
        }
    }
    ans.push([prev[0], prev[1]]);
    return ans;
};
