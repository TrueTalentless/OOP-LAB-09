import org.junit.Before;
import org.junit.Test;

import javax.swing.table.DefaultTableModel;

import static org.junit.Assert.*;

public class MainTest {
    private Main main;
    private DefaultTableModel tableModel;

    @Before
    public void setUp() {
        main = new Main();
        String[] columns = {"Кличка", "Порода", "Владелец", "Судья", "Награды"};
        tableModel = new DefaultTableModel(columns, 0);
        main.show(); // Инициализируем GUI для тестов
        main.getTableModel().setRowCount(0); // Удаляем все строки для изоляции тестов
    }

    @Test
    public void testAddDog() {
        main.addDog(new Object[]{"Рекс", "Немецкая овчарка", "Иванов", "Петров", "Чемпион"});
        assertEquals(1, main.getTableModel().getRowCount());
    }

    @Test
    public void testEditDog() {
        main.addDog(new Object[]{"Рекс", "Немецкая овчарка", "Иванов", "Петров", "Чемпион"});
        main.editDog(0, new Object[]{"Барон", "Доберман", "Сидоров", "Иванов", "Победитель"});

        assertEquals("Барон", main.getTableModel().getValueAt(0, 0));
        assertEquals("Доберман", main.getTableModel().getValueAt(0, 1));
        assertEquals("Сидоров", main.getTableModel().getValueAt(0, 2));
    }


    @Test
    public void testDeleteDog() {
        main.addDog(new Object[]{"Рекс", "Немецкая овчарка", "Иванов", "Петров", "Чемпион"});
        main.deleteDog(0);
        assertEquals(0, tableModel.getRowCount());
    }

    @Test
    public void testSaveData() {
        main.addDog(new Object[]{"Рекс", "Немецкая овчарка", "Иванов", "Петров", "Чемпион"});
        main.saveDataToFile();
        assertFalse(main.unsavedChanges);
    }
}
