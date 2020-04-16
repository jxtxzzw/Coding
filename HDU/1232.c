#include <stdio.h>

#define MAXN 1007

int disjointSet[MAXN] = { 0 };
int height[MAXN] = { 0 };

int findSet(int cityIndex);
void join(int cityIndexA, int cityIndexB);

int main(int argc, char const *argv[]) {
	int numberOfCities = 0;
	int numberOfRoads = 0;

	while (scanf("%d", &numberOfCities) == 1 && numberOfCities != 0) {
		// 读入数据，将每个城市列为单独的并查集，并设置高度为1
		scanf("%d", &numberOfRoads);
		for (int cityIndex = 1; cityIndex <= numberOfCities; ++cityIndex) {
			disjointSet[cityIndex] = cityIndex;
			height[cityIndex] = 1;
		}
		for (int cityIndex = 0; cityIndex < numberOfRoads; ++cityIndex) {
			int cityIndexA = 0;
			int cityIndexB = 0;
			scanf("%d%d", &cityIndexA, &cityIndexB);
			if (findSet(cityIndexA) != findSet(cityIndexB)) {
				// 如果两个城市不属于同一个并查集，合并，表示这两个城市有路
				join(cityIndexA, cityIndexB);
			}
		}
		// 计数器记录连通块的个数，即父节点为自身的城市的个数
		int counter = 0;
		for (int cityIndex = 1; cityIndex <= numberOfCities; ++cityIndex) {
			if (disjointSet[cityIndex] == cityIndex) {
				counter++;
			}

		}
		// 还需要修建的路就是连通块的个数减1
		printf("%d\n", counter - 1);
	}
	return 0;
}
/**
 * 查
 * @param cityIndex - 城市编号
 * @return 城市属于的那个并查集的编号
 * @note 查询过程带路径优化
 * 
 */
int findSet(int cityIndex) {
	// 假设这城市就是可能的根节点
	int possibleRoot = cityIndex;
	while (disjointSet[possibleRoot] != possibleRoot) {
		// 当发现不是的时候，逐层往上
		possibleRoot = disjointSet[possibleRoot];
	}
	// 这时的possibleRoot就是真正的root了

	int root = possibleRoot;
	// 开始路径压缩优化
	int modifier = cityIndex;
	while (modifier != root) {
		// 循环修改查找路径上的所有节点
		int nextCheckPoint = disjointSet[modifier];
		disjointSet[modifier] = root;
		modifier = nextCheckPoint;
	}
	return root; // 返回root即为所属并查集编号
}

/**
 * 并
 * @param cityIndexA - 城市A编号
 * @param cityIndexB - 城市B编号
 */
void join(int cityIndexA, int cityIndexB) {
	// 根据城市编号，获得并记录所属并查集编号
	int disjointSetIndexA = findSet(cityIndexA);
	int disjointSetIndexB = findSet(cityIndexB);
	// 如果高度一样，无所谓合并到哪个
	if (height[disjointSetIndexA] == height[disjointSetIndexB]) {
		height[disjointSetIndexA] = height[disjointSetIndexA] + 1;
		disjointSet[disjointSetIndexB] = disjointSetIndexA;
	} else {
		// 否则合并到高度浅的那个上面
		if (height[disjointSetIndexA] < height[disjointSetIndexB]) {
			disjointSet[disjointSetIndexA] = disjointSetIndexB;
		} else {
			disjointSet[disjointSetIndexB] = disjointSetIndexA;
		}
	}
}
