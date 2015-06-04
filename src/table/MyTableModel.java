package table;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MyTableModel extends AbstractTableModel {

    private final String[] columnNames = new String[]{"X", "Y"};
    private DecimalFormat myFormatter = new DecimalFormat("##0.###");
    private List<Double[]> resultData = new ArrayList<Double[]>();

    public MyTableModel() {
    }

    @Override
    public int getRowCount() {
        return resultData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return myFormatter.format(resultData.get(rowIndex)[0]);
            case 1:
                return myFormatter.format(resultData.get(rowIndex)[1]);
        }
        return 0;
    }

    public void updateModel(List<Double[]> resultData) {
        this.resultData = resultData;
        fireTableDataChanged();
    }
}
