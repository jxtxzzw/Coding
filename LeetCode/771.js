var numJewelsInStones = function(J, S) {
    let count = 0;
    for (c of S) {
        if (J.includes(c)) {
            count++;
        }
    }
    return count;
};
