package sorting;

/**
 * Created by USER on 17.05.15.
 */
public class Tree {
    public Tree left;            // левое и правое поддеревья и ключ
    public Tree right;
    public int key;

    public Tree(int k) {        // конструктор с инициализацией ключа
        key = k;
    }

    public void insert( Tree aTree) {
        if ( aTree.key < key )
            if ( left != null ) left.insert( aTree );
            else left = aTree;
        else
        if ( right != null ) right.insert( aTree );
        else right = aTree;
    }

    public void traverse(TreeVisitor visitor) {
        if ( left != null)
            left.traverse( visitor );

        visitor.visit(this);

        if ( right != null )
            right.traverse( visitor );
    }
}
