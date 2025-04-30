class NotesMenu(private val archive: Archive) : Menu() {
    init {
        menuItems.add(MenuItem("Создать заметку") {
            createNote()
            updateMenuItems()
        })
    }

    override fun getMenuTitle() = "Список заметок в архиве \"${archive.name}\""
    override fun getExitOption() = "Назад"

    private fun createNote() {
        println("\nСоздание заметки:")
        print("Введите название заметки: ")
        val name = scanner.nextLine().trim()
        if (name.isEmpty()) {
            println("Ошибка: название заметки не может быть пустым.")
            return
        }

        print("Введите текст заметки: ")
        val text = scanner.nextLine().trim()
        if (text.isEmpty()) {
            println("Ошибка: текст заметки не может быть пустым.")
            return
        }

        archive.notes.add(Note(name, text))
        println("Заметка \"$name\" создана.")
    }

    fun start() {
        updateMenuItems()
        super.show()
    }

    private fun updateMenuItems() {
        menuItems.clear()
        menuItems.add(MenuItem("Создать заметку") {
            createNote()
            updateMenuItems()
        })

        archive.notes.forEach { note ->
            menuItems.add(MenuItem(note.name) { showNote(note) })
        }
    }

    private fun showNote(note: Note) {
        println("\nЗаметка: ${note.name}")
        println("Текст: ${note.text}")
        println("\nНажмите Enter, чтобы вернуться...")
        scanner.nextLine()
    }
}
