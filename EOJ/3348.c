#include <stdio.h>
#include <stdlib.h>

#define MAXN 100007

/*
 * 这道题有一组数据很恶心
 * 有100000个节点
 * 然后双亲是-1 0 0 0 0 ……
 * 也就是说
 * 只有一个编号为0的根节点
 * 剩下的99999个都是他的子树
 * 于是就是一个高度为1的99999叉树
 * 所以不能用数组存
 * 会MLE
 * 要用链表
 */

typedef struct link_node {
    int data;
    struct link_node *child; // 子树
    struct link_node *next; // 链表的下一个，即与自己并列的属于同一个父亲的兄弟树
    struct link_node *prev; // 链表的前一个
    struct link_node *last; // 子树们的最后一个
    /*
     * 这里做一个优化，直接保留子树们的最后一个
     * 这是减少了链表的next的next的next直到找对最后一个NULL
     * 这样就可以直接取到最后一个了
     */
} LINK_NODE;

LINK_NODE *Node_initail();
LINK_NODE *creat_tree_fromParent(int);
void r_postorder(LINK_NODE*);

LINK_NODE *Node_initail() {
    LINK_NODE* node = (LINK_NODE*) malloc(sizeof(LINK_NODE));
    // 初始化数据域
    node->data = 0;
    node->parent = NULL;
    node->child = NULL;
    node->next=NULL;
    node->prev = NULL;
    node->last = NULL;
    return node;
}

LINK_NODE *creat_tree_fromParent(int n) {
    LINK_NODE *addr_NODE[MAXN]={0};
    LINK_NODE* root = Node_initail();
    root->data = 0;
    scanf("%*d");
    addr_NODE[0] = root; // 因为根节点一定存在，所以直接处理掉
    for (int i = 1; i < n; ++i) {
        int parentIndex;
        scanf("%d",&parentIndex); // 读入父亲
        LINK_NODE* currentNode = addr_NODE[i]; // 取出这个节点
        if (currentNode==NULL) currentNode = Node_initail(); // 如果为NULL说明没有遇到过，就new一个给他
        currentNode->data = i;
        addr_NODE[i] = currentNode;
        LINK_NODE* parentNode = addr_NODE[parentIndex]; // 找到父亲
        if (parentNode==NULL) { // 如果父亲不存在就new一个给他，然后留下父亲
            parentNode = Node_initail();
            addr_NODE[parentIndex] = parentNode;
        }
        LINK_NODE* p = parentNode;
        if (p->child==NULL){
            // 如果还没有孩子，就把这个作为孩子，然后更新last
            p->child = currentNode;
            p->child->last = p->child;
        } else {
            // 如果已经存在孩子，直接找到最后一个孩子，在最后一个孩子的next赋值为这个node，然后更新last
            p->child->last->next = currentNode;
            currentNode->prev = p->child->last;
            p->child->last = p->child->last->next;
        }
    }
    return root;
}
void r_postorder(LINK_NODE *t) {
    // 递归遍历
    if (t != NULL) {
        if (t->child!=NULL){
            LINK_NODE* p = t->child->last;
            // 从最后一个往前遍历
            do {
                r_postorder(p);
                p = p->prev;
            }while (p!=NULL);
        }
        printf("%d ", t->data);
    }
}

int main() {
    LINK_NODE *root;
    int n;
    scanf("%d\n", &n);
    root = creat_tree_fromParent(n);
    r_postorder(root);
    printf("\n");
    return 0;
}
