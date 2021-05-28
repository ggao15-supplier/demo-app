//
// Created by gggao on 5/28/2021.
//

#include<stdio.h>
#include<stdlib.h>

typedef struct TreeNode{
    int value;
    struct TreeNode* left;
    struct TreeNode* right;
} Tree;



Tree* initTree(){
    Tree* root = malloc(sizeof(Tree));
    root->value = 1;

    Tree* t1 = malloc(sizeof(Tree));
    t1->value = 2;
    root->left = t1;

    Tree* t2 = malloc(sizeof(Tree));
    t2->value = 3;
    t1->left = t2;
    t2->left = NULL;
    t2->right = NULL;

    Tree* t3 = malloc(sizeof(Tree));
    t3->value = 4;
    t1->right = t3;
    t3->left = NULL;
    t3->right = NULL;

    Tree* t4 = malloc(sizeof(Tree));
    t4->value = 5;
    root->right = t4;

    Tree* t5 = malloc(sizeof(Tree));
    t5->value = 6;
    t4->left = t5;
    t5->left = NULL;
    t5->right = NULL;

    Tree* t6 = malloc(sizeof(Tree));
    t6->value = 7;
    t4->right = t6;
    t6->left = NULL;
    t6->right = NULL;

    return root;
}

void middlePrint(Tree* root){
    if(root->left != NULL){
        middlePrint(root->left);
    }
    printf("value is: %d\n",root->value);

    if(root->right != NULL){
        middlePrint(root->right);
    }
}

void prePrint(Tree* root){
    printf("value is: %d\n",root->value);
    if(root->left != NULL){
        prePrint(root->left);
    }

    if(root->right != NULL){
        prePrint(root->right);
    }

}

void nextPrint(Tree* root){

    if(root->left != NULL){
        nextPrint(root->left);
    }

    if(root->right != NULL){
        nextPrint(root->right);
    }
    printf("value is: %d\n",root->value);
}

Tree* refactor(int pre[], int middle[], int length){

    Tree* root = malloc(sizeof(Tree));
    int index = 0;
    for(index = 0; index < length; index++){
        int middleValue = middle[index];
        Tree* middleTree = malloc(sizeof(Tree));
        middleTree->value = middleValue;
    }
}
int main(void){
    Tree* root = initTree();

    middlePrint(root);
    printf("---------------------------------\n");
    prePrint(root);
    printf("---------------------------------\n");
    nextPrint(root);
    printf("-------------refactor tree-----------------\n");

    int preArray[] = {3,2,4,1,6,5,7};
    int middleArray[] = {1,2,3,4,5,6,7};
    return 0;
}