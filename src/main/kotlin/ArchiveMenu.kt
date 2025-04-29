class ArchiveMenu : Menu() {
    private val archives = mutableListOf<Archive>()

    init {
        menuItems.add(MenuItem("Создать архив") { createArchive() })
    }

    override fun getMenuTitle() = "Список архивов"
    override fun getExitOption() = "Выход"

    private fun createArchive() {
        println("\nСоздание архива:")
        print("Введите название архива: ")
        val name = scanner.nextLine().trim()
        if (name.isEmpty()) {
            println("Ошибка: название архива не может быть пустым.")
            return
        }
        archives.add(Archive(name))
        println("Архив \"$name\" создан.")
    }

    fun start() {
        while (true) {
            updateMenuItems()
            super.show()
        }
    }

    private fun updateMenuItems() {
        menuItems.clear()
        menuItems.add(MenuItem("Создать архив") { createArchive() })
        archives.forEach { archive ->
            menuItems.add(MenuItem(archive.name) { openNotesMenu(archive) })
        }
    }

    private fun openNotesMenu(archive: Archive) {
        NotesMenu(archive).start()
    }
}
