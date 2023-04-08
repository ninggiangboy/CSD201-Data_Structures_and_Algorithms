package data_structures.tree;

public class BSTree<E extends Comparable<E>> extends BinaryTree<E> {

    public BSTree() {

    }

    protected Node<E> search(Node<E> p, E e) {
        if (p == null) {
            return null;
        }
        if (e.equals(p.data)) {
            return p;
        }

        return e.compareTo(p.data) < 0
                ? search(p.left, e)
                : search(p.right, e);
    }

    public void insert(E e) {
        if (isEmpty()) {
            root = new Node<>(e);
            return;
        }

        Node<E> X = new Node<>(e);
        Node<E> p = root, parent = root;
        int cmp;
        while (p != null) {
            parent = p;
            cmp = p.data.compareTo(e);
            if (cmp > 0) {
                p = p.left;
            } else if (cmp < 0) {
                p = p.right;
            } else {
                return;
            }
        }

        if (e.compareTo(parent.data) < 0) {
            parent.left = X;
        } else {
            parent.right = X;
        }
    }

    public void deleteByCopy(E e) {
        if (isEmpty()) {
            return;
        }

        Node<E>[] searched = findNodeAndParent(root, e);
        Node<E> p = searched[0], parent = searched[1], rightMost;

        if (p == null) {
            return;
        }

        int numChild = numOfChildren(p);

        if (numChild == 0) {
            parent = null;
        } else if (numChild == 1) {
            if (parent.left == p) {
                parent.left = p.right;
            } else {
                parent.right = p.right;
            }
        } else {
            rightMost = p.left;
            Node<E> parRightMost = parent(rightMost);

            p.data = rightMost.data;
            if (parRightMost.left == rightMost) {
                parRightMost.left = rightMost.left;
            } else {
                parRightMost.right = rightMost.left;
            }
        }
    }

    protected Node<E> minNode(Node<E> p) {
        Node<E> rightMost = p;
        while (rightMost.right != null) {
            rightMost = rightMost.right;
        }
        return rightMost;
    }

    public void deleteByMerging(E x) {
        if (isEmpty()) {
            return;
        }
        Node<E> p = root, par = root, rightMost = root;
        int numChild = numOfChildren(root);
        if (root.data.equals(x)) {
            if (numChild == 0) {
                root = null;
            } else if (numChild == 1) {
                root = root.right;
            } else if (numChild == -1) {
                root = root.left;
            } else {
                rightMost = minNode(root.right);
                rightMost.right = root.right;
                root = root.left;
            }
            return;
        }

        int cmp;
        while (p != null) {
            cmp = p.data.compareTo(x);
            if (cmp == 0) {
                break;
            } else if (cmp > 0) {
                par = p;
                p = p.left;
            } else {
                par = p;
                p = p.right;
            }
        }
        if (p == null) {
            return;
        }

        numChild = numOfChildren(p);
        if (numChild == 0) {
            if (par.left == p) {
                par.left = null;
            } else {
                par.right = null;
            }
        } else if (numChild == -1) {
            if (par.left == p) {
                par.left = p.left;
            } else {
                par.right = p.left;
            }
        } else if (numChild == 1) {
            if (par.left == p) {
                par.left = p.right;
            } else {
                par.right = p.right;
            }
        } else {
            rightMost = minNode(p.left);
            if (par.left == p) {
                par.left = p.left;
            } else {
                par.right = p.left;
            }
            rightMost.right = p.right;
        }
    }

    protected void leftRotate(E e) {
        leftRotate(search(root, e));
    }

    protected void leftRotate(Node<E> p) {
        Node<E> parent = parent(p);
        if (p == null) {
            return;
        }
        if (p == root) {
            root = leftRotateNode(p);
        } else {
            if (parent.right == p) {
                parent.right = leftRotateNode(p);
            } else {
                parent.left = leftRotateNode(p);
            }
        }
    }

    protected Node<E> leftRotateNode(Node<E> p) {
        if (p.right == null) {
            return p;
        }
        Node<E> q = p.right;
        p.right = q.left;
        q.left = p;
        return q;
    }

    protected void rightRotate(E e) {
        rightRotate(search(root, e));
    }

    protected void rightRotate(Node<E> p) {
        Node<E> parent = parent(p);
        if (p == null) {
            return;
        }
        if (p == root) {
            root = rightRotateNode(p);
        } else {
            if (parent.left == p) {
                parent.left = rightRotateNode(p);
            } else {
                parent.right = rightRotateNode(p);
            }
        }
    }

    protected Node<E> rightRotateNode(Node<E> p) {
        if (p.left == null) {
            return p;
        }
        Node<E> q = p.left;
        p.left = q.right;
        q.right = p;
        return q;
    }

    protected void leftRightRotate(E e) {
        leftRightRotate(search(root, e));
    }

    protected void leftRightRotate(Node<E> p) {
        Node<E> parent = parent(p);
        if (p == null) {
            return;
        }
        if (p == root) {
            root = leftRightRotateNode(p);
        } else {
            if (parent.right == p) {
                parent.right = leftRightRotateNode(p);
            } else {
                parent.left = leftRightRotateNode(p);
            }
        }
    }

    protected Node<E> leftRightRotateNode(Node<E> p) {
        if (p.left.right == null) {
            return p;
        }
        p.left = leftRotateNode(p.left);
        return rightRotateNode(p);
    }

    protected void rightLeftRotate(E e) {
        rightLeftRotate(search(root, e));
    }

    protected void rightLeftRotate(Node<E> p) {
        Node<E> parent = parent(p);
        if (p == null) {
            return;
        }
        if (p == root) {
            root = rightLeftRotateNode(p);
        } else {
            if (parent.right == p) {
                parent.right = rightLeftRotateNode(p);
            } else {
                parent.left = rightLeftRotateNode(p);
            }
        }
    }

    protected Node<E> rightLeftRotateNode(Node<E> p) {
        if (p.right.left == null) {
            return p;
        }
        p.right = rightRotateNode(p.right);
        return leftRotateNode(p);
    }

    protected Node<E> parent(Node<E> p) {
        Node<E> parent = null;
        Node<E> current = root;
        int cmp;

        while (current != null) {
            cmp = current.data.compareTo(p.data);
            if (cmp == 0) {
                return parent;
            } else if (cmp > 0) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }
        return null;
    }

    protected Node<E>[] findNodeAndParent(Node<E> current, E value) {
        Node<E> p = current, parent = null;

        while (p != null && p.data.compareTo(value) != 0) {
            parent = p;
            if (p.data.compareTo(value) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        @SuppressWarnings("unchecked")
        Node<E>[] result = (Node<E>[]) new Node[2];
        result[0] = p;
        result[1] = parent;
        return result;
    }
}
