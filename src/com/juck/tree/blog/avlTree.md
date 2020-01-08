# 自平衡二叉排序树(AVL Tree)

[TOC]



## 1 数据结构

### 1.1 节点结构

&emsp;&emsp;每个节点除了存储数据外，还会有两个引用，分别指向以自身节点为根节点的左孩子和右孩子。

![treeNode](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200108212337506-520580943.png)

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
    public E remove(E e) {
        return null;
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
    
    private static class Node<E extends Comparable<E>> {
        E item;
        Node<E> left;
        Node<E> right;

        public Node(E item) {
            this.item = item;
        }

        private void add(Node<E> node) {
            if (this.item.compareTo(node.item) > 0) {
                if (Objects.isNull(this.left)) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else if (this.item.compareTo(node.item) < 0) {
                if (Objects.isNull(this.right)) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            } else {
                this.item = node.item;
            }

            // adjust tree to meet requirements of AVL Tree
            this.avlTreeify();
        }

        private void avlTreeify() {}

        private int leftHeight() {
            return Objects.isNull(this.left) ? 0 : this.left.height();
        }

        private int rightHeight() {
            return Objects.isNull(this.right) ? 0 : this.right.height();
        }

        /**
         * height of the tree rooted with this node,
         * the height is the max height of its left sub-tree height and right sub-tree height then add 1
         * add 1: the height of this node itself
         */
        private int height() {
            int leftHeight = Objects.isNull(this.left) ? 0 : this.left.height();
            int rightHeight = Objects.isNull(this.right) ? 0 : this.right.height();

            return Math.max(leftHeight, rightHeight) + 1;
        }

        private void leftRotate() {
            Node<E> newNode = new Node<>(this.item);
            newNode.right = this.right.left;
            newNode.left = this.left;
            this.item = this.right.item;
            this.right = this.right.right;
            this.left = newNode;
        }

        private void rightRotate() {
            Node<E> newNode = new Node<>(this.item);
            newNode.left = this.left.right;
            newNode.right = this.right;
            this.item = this.left.item;
            this.left = this.left.left;
            this.right = newNode;
        }
    }
}
```

#### 2.2.1 方法实现

&emsp;&emsp; `AVL Tree` 操作基本都和 `二叉排序树` 一样，只是在添加和删除节点时可能要对节点进行旋转操作，这里只写出 `add` 实现，完整代码可以去 `git` 查看。

&emsp;&emsp;添加节点时，可能出现以下情况破坏了左右子树高度之差不大于1的条件：

1. RR 旋转，插入节点 **`1`** 后使得节点 **`X`** 左子树 `XL` 高度比右子树 `XR` 高度且左子树 `XL` 的左子树 `2` 高度大于左子树 `XL` 的右子树 `null` 高度。

&emsp;&emsp;1. 以 `X` 节点存储的内容新建一个 `newNode` ，`Node newNode = newNode(this.item)`
&emsp;&emsp;2. 将 `newNode` 的左孩子设置为 `X` 的左孩子的右孩子， `newNode.left = this.left.right` 
&emsp;&emsp;3.  `newNode` 的右孩子设置为 `X` 的右孩子，`newNode.right = this.right`
&emsp;&emsp;4. 将 `X` 节点存储的内容设置为 `X` 的左孩子的内容，`this.item = this.left.item`
&emsp;&emsp;5. 将 `X` 节点的左孩子设置为 `X` 的左孩子的左孩子，`this.left = this.left.left`
&emsp;&emsp;6. 将 `X` 节点的右孩子设置为 `newNode`，`this.right = newNode` 

| 初始                                                         | 过程                                                         | 结果                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![LL1](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200108212627338-1630330962.png) | ![LL2](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200108212651232-1994388305.png) | ![LL3](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200108212738972-1087693889.png) |

```java
private void rightRotate() {
    Node<E> newNode = new Node<>(this.item);
    newNode.left = this.left.right;
    newNode.right = this.right;
    this.item = this.left.item;
    this.left = this.left.left;
    this.right = newNode;
}
```

2. LL旋转，插入节点 **`8`** 后使得节点 **`X`** 右子树 `XR` 高度比左子树 `XL` 高度且右子树 `XR` 的右子树 `7` 高度大于右子树 `XR` 的左子树 `null` 高度。操作与RR相反

&emsp;&emsp;1. 以 `X` 节点存储的内容新建一个 `newNode` ，`Node newNode = newNode(this.item)`
&emsp;&emsp;2. 将 `newNode` 的右孩子设置为 `X` 的右孩子的左孩子， `newNode.right = this.right.left` 
&emsp;&emsp;3.  `newNode` 的左孩子设置为 `X` 的左孩子，`newNode.left = this.left`
&emsp;&emsp;4. 将 `X` 节点存储的内容设置为 `X` 的右孩子的内容，`this.item = this.right.item`
&emsp;&emsp;5. 将 `X` 节点的右孩子设置为 `X` 的右孩子的右孩子，`this.right= this.right.right`
&emsp;&emsp;6. 将 `X` 节点的左孩子设置为 `newNode`，`this.left= newNode` 

|                             初始                             |                             过程                             |                             结果                             |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
| ![RR1](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200108215951873-1398189222.png) | ![RR2](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200108220011070-196424067.png) | ![RR3](https://img2018.cnblogs.com/blog/1509943/202001/1509943-20200108220026208-270979975.png) |

```java
private void leftRotate() {
    Node<E> newNode = new Node<>(this.item);
    newNode.right = this.right.left;
    newNode.left = this.left;
    this.item = this.right.item;
    this.right = this.right.right;
    this.left = newNode;
}
```

