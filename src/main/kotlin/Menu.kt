import java.util.Scanner

abstract class Menu {
    protected val scanner = Scanner(System.`in`)
    protected val menuItems = mutableListOf<MenuItem>()

    fun show() {
        while (true) {
            printMenu()
            val input = readInput()
            if (input == -1) continue
            if (input == 0) break
            executeMenuItem(input)
        }
    }

    private fun printMenu() {
        println("\n${getMenuTitle()}:")
        menuItems.forEachIndexed { index, item ->
            println("${index + 1}. ${item.name}")
        }
        println("0. ${getExitOption()}")
    }

    private fun readInput(): Int {
        print("Введите номер пункта: ")
        return try {
            val input = scanner.nextLine().toInt()
            if (input !in 0..menuItems.size) {
                println("Ошибка: такого пункта нет. Введите число от 0 до ${menuItems.size}.")
                -1
            } else {
                input
            }
        } catch (e: NumberFormatException) {
            println("Ошибка: введите число.")
            -1
        }
    }

    private fun executeMenuItem(input: Int) {
        menuItems[input - 1].action()
    }

    abstract fun getMenuTitle(): String
    abstract fun getExitOption(): String
}
