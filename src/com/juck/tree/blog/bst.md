# 二叉排序树

[TOC]

## 1 数据结构

### 1.1 节点结构

&emsp;&emsp;每个节点除了存储数据外， 还会有两个引用， 分别指向以自身节点为根节点的左孩子和右孩子。

![treeNode](../resources/treeNode.png)

### 1.2 二叉排序树结构

&emsp;&emsp;一颗二叉排序树要么为空树， 要么就要满足以下条件:

1.   若左子树不空，则左子树上所有结点的值均小于它的根结点的值;
2.  若右子树不空，则右子树上所有结点的值均大于它的根结点的值;
3.  左、右子树也分别为二叉排序树;

&emsp;&emsp;因为以上条件的限制， 所以**二叉排序树使用中序遍历得到的一定是递增的有序序列**。记住这句话。

|                    正解                    |                  错误                  |
| :----------------------------------------: | :------------------------------------: |
| ![correctBst](../resources/correctBst.png) | ![wrongBst](../resources/wrongBst.png) |

## 2 代码实现

### 2.1 接口

&emsp;&emsp;因为二叉排序树的节点是有大小关系的， 所以泛型需要实现 `Comparable` 实现可比较。

```java
public interface ITree<E extends Comparable<E>> {
    boolean add(E e);

    E remove(E e);

    boolean search(E e);
}
```

### 2.2 实现类

```java
public class BSTree<E extends Comparable<E>> implements ITree<E> {
    private Node<E> root;
    
    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node<>(e);
        if (Objects.isNull(root)) {
            root = newNode;
        } else {
            root.add(newNode);
        }

        return true;
    }

    @Override
    public E remove(E e) {
        root = doRmove(root, e);
        return null;
    }

    @Override
    public boolean search(E e) {
        return false;
    }
    
    private static class Node<E extends Comparable<E>> {
        E item;
        Node<E> left;
        Node<E> right;

        public Node(E item) {
            this.item = item;
        }

        public void add(Node<E> node) {
            if (this.item.compareTo(node.item) > 0) {
                if (Objects.isNull(this.left)) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else {
                if (Objects.isNull(this.right)) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }
    }
}
```

