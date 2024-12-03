import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Класс для тестирования методов класса {@link Main}.
 */
public class MainTest {
    private Main main;

    /**
     * Инициализация тестовой среды.
     */
    @Before
    public void setUp() {
        main = new Main();
        main.show();
        main.getTableModel().setRowCount(0); // Очистка таблицы перед каждым тестом
    }

    /**
     * Тестирует метод {@link Main#addDog(Object[])}.
     */
    @Test
    public void testAddDog() {
        main.addDog(new Object[]{"Рекс", "Немецкая овчарка", "Иванов", "Петров", "Чемпион"});
        assertEquals(1, main.getTableModel().getRowCount());
    }

    /**
     * Тестирует метод {@link Main#editDog(int, Object[])}.
     */
    @Test
    public void testEditDog() {
        main.addDog(new Object[]{"Рекс", "Немецкая овчарка", "Иванов", "Петров", "Чемпион"});
        main.editDog(0, new Object[]{"Барон", "Доберман", "Сидоров", "Иванов", "Победитель"});
        assertEquals("Барон", main.getTableModel().getValueAt(0, 0));
        assertEquals("Доберман", main.getTableModel().getValueAt(0, 1));
    }

    /**
     * Тестирует метод {@link Main#deleteDog(int)}.
     */
    @Test
    public void testDeleteDog() {
        main.addDog(new Object[]{"Рекс", "Немецкая овчарка", "Иванов", "Петров", "Чемпион"});
        main.deleteDog(0);
        assertEquals(0, main.getTableModel().getRowCount());
    }

    /**
     * Тестирует метод {@link Main#saveDataToFile()}.
     */
    @Test
    public void testSaveData() {
        main.addDog(new Object[]{"Рекс", "Немецкая овчарка", "Иванов", "Петров", "Чемпион"});
        main.saveDataToFile();
        assertFalse(main.unsavedChanges);
    }
}
