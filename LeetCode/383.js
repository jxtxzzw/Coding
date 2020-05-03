var canConstruct = function (ransomNote, magazine) {
    let m = new Map();
    magazine.split("").map((c) => m.set(c, m.has(c) ? m.get(c) + 1 : 1));
    ransomNote.split("").map((c) => m.set(c, m.has(c) ? m.get(c) - 1 : -1));
    return Math.min.apply(null, Array.from(m.values())) >= 0;
};
