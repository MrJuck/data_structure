# 自平衡二叉排序树(AVL Tree)

[TOC]



## 1 数据结构

### 1.1 节点结构

&emsp;&emsp;每个节点除了存储数据外，还会有两个引用，分别指向以自身节点为根节点的左孩子和右孩子。

![treeNode](D:/F_IDEA_Workspace/data_structure/src/com/juck/tree/resources/treeNode.png)

### 1.2 AVL Tree结构

&emsp;&emsp;首先 `AVL Tree` 是一颗二叉排序树, 所以它要满足二叉排序树的条件，除此外，它还要满足以下条件: 

*  每个节点的左右子树的高度之差的绝对值（平衡因子）最多为1。

|       | 示例图                                                       | 理由                                                         |
| ----- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 正解  | ![correctAVLTree](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200107204320851-1184950418.png) | 满足二叉排序树的条件且每个节点的左右子树的高度之差均小于1。  |
| 错误1 | ![wrongAVLTree1](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200107204228907-543547174.png) | 满足二叉排序树的条件但是 `4` 的左子树高度为 `2` 右子树高度为 `0` ，左右子树高度大于1。 |
| 错误2 | ![wrongAVLTree2](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200107204102211-1495996257.png) | 满足二叉排序树的条件但是 `4` 的右子树高度为 `2` 左子树高度为 `0` ，左右子树高度大于1。 |

## 2 代码实现

&emsp;&emsp;代码与二叉排序树几乎一样，只是在插入和删除节点后可能会破坏AVL Tree 左右子树高度不大于1 的规则， 所以需要对节点进行旋转来让树重新满足平衡条件。

&emsp;&emsp;另外，因为二叉排序树中的方法都写在树中，所以此次的实现我会将方法写在Node节点中。当然道理都一样，只是对于面向对象而言，这个属性、方法究竟是属于谁这是个问题，我不深究，两种方法我都写出来。小孩子才做选择，我都要。

### 2.1 接口

```java
public interface ITree<E extends Comparable<E>> {
    boolean add(E e);

    E remove(E e);

    E search(E e);
}
```

### 2.2 实现类

```java
public class AVLTree<E extends Comparable<E>> implements ITree<E> {
    private Node<E> root;

    @Override
    public boolean add(E e) {
        if (Objects.isNull(e)) {
            throw new RuntimeException("Element to be inserted must not be null");
        }

        Node<E> newNode = new Node<>(e);
        if (Objects.isNull(root)) {
            root = newNode;
        } else {
            root.add(newNode);
        }

        return true;
    }

    @Override
    public boolean remove(E e) {
        return false;
    }

    @Override
    public E search(E e) {
        if (Objects.isNull(e)) {
            throw new RuntimeException("null element is not allowed!");
        }

        if (Objects.isNull(root)) {
            return null;
        }

        Node<E> result = root.search(e);
        return Objects.isNull(result) ? null : result.item;
    }
}
```

