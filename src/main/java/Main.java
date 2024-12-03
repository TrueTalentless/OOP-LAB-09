import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Лебедев Игнат 3312
 * @version 1.0
 */
public class Main {
    private JFrame mainFrame;
    private DefaultTableModel tableModel;
    private JTable dataTable;
    public boolean unsavedChanges = false;

    public void show() {
        mainFrame = new JFrame("Dog Show Administration");
        mainFrame.setSize(800, 400);
        mainFrame.setLocation(100, 100);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JButton addDogButton = new JButton("Добавить");
        JButton editDogButton = new JButton("Изменить");
        JButton deleteDogButton = new JButton("Удалить");
        JButton saveDogButton = new JButton("Сохранить");

        JToolBar toolBar = new JToolBar("Панель инструментов");
        toolBar.add(addDogButton);
        toolBar.add(editDogButton);
        toolBar.add(deleteDogButton);
        toolBar.add(saveDogButton);

        mainPanel.add(toolBar, BorderLayout.NORTH);

        String[] columns = {"Кличка", "Порода", "Владелец", "Судья", "Награды"};
        tableModel = new DefaultTableModel(columns, 0);
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainFrame.add(mainPanel);

        addDogButton.addActionListener(e -> {
            addDog(new Object[]{"Новая собака", "Неизвестная порода", "Новый владелец", "Новый судья", "Нет наград"});
            JOptionPane.showMessageDialog(mainFrame, "Добавлена новая собака");
        });

        editDogButton.addActionListener(e -> {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(mainFrame, "Пожалуйста, выберите строку для редактирования");
                return;
            }
            editDog(selectedRow, new Object[]{"Обновленная собака", "Обновленная порода", "Обновленный владелец", "Обновленный судья", "Обновленная награда"});
            JOptionPane.showMessageDialog(mainFrame, "Информация о собаке обновлена");
        });

        deleteDogButton.addActionListener(e -> {
            int selectedRow = dataTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(mainFrame, "Пожалуйста, выберите строку для удаления");
                return;
            }
            deleteDog(selectedRow);
            JOptionPane.showMessageDialog(mainFrame, "Выбранная запись удалена");
        });

        saveDogButton.addActionListener(e -> {
            saveDataToFile();
            JOptionPane.showMessageDialog(mainFrame, "Данные успешно сохранены");
        });

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (unsavedChanges) {
                    int response = JOptionPane.showConfirmDialog(
                            mainFrame,
                            "Есть несохраненные изменения. Хотите сохранить перед выходом?",
                            "Несохраненные изменения",
                            JOptionPane.YES_NO_CANCEL_OPTION
                    );
                    if (response == JOptionPane.YES_OPTION) {
                        saveDataToFile();
                        mainFrame.dispose();
                    } else if (response == JOptionPane.NO_OPTION) {
                        mainFrame.dispose();
                    }
                } else {
                    mainFrame.dispose();
                }
            }
        });

        mainFrame.setVisible(true);
    }

    public void addDog(Object[] rowData) {
        tableModel.addRow(rowData);
        unsavedChanges = true;
    }

    public void editDog(int rowIndex, Object[] updatedData) {
        for (int i = 0; i < updatedData.length; i++) {
            tableModel.setValueAt(updatedData[i], rowIndex, i);
        }
        unsavedChanges = true;
    }

    public void deleteDog(int rowIndex) {
        tableModel.removeRow(rowIndex);
        unsavedChanges = true;
    }

    public void saveDataToFile() {
        unsavedChanges = false;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public static void main(String[] args) {
        new Main().show();
    }
}
