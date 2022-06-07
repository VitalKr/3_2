class NoteService : CrudService<Note> {

    override fun add(note: Note): Note {
        val newNote = note.copy(id = notes.size + 1, deleted = false)
        notes.add(newNote)
        println(notes.last())
        return notes.last()
    }

    override fun delete(id: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (!note.deleted) {
                if (id == note.id) {
                    notes[index] = note.copy(id = note.id, deleted = true)
                    return true
                }
            }
        }
        throw NoteException("Такой заметки нет")
    }

    override fun edit(id: Int, newNote: Note): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (!note.deleted) {
                if (id == note.id) {
                    notes[index] = note.copy(
                        id = note.id, ownerId = note.id, title = newNote.title,
                        text = newNote.text, deleted = false
                    )
                    println(notes[index])
                    return true
                }
            }
        }
        throw NoteException("Такой заметки нет")
    }

    override fun getById(id: Int) {
        for (note in notes) {
            if (id == note.id) {
                if (!note.deleted) {
                    println("title: ${note.title}, text: ${note.text}")
                }
            }
        }
    }

    override fun restore(id: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (id == note.id) {
                if (note.deleted) {
                    notes[index] = note.copy(id = note.id, deleted = false)
                    println("Заметка ${note.id} восстановлена.")
                    return true
                }
            }
        }
        throw CommentRestoreException("Такой заметки нет")
    }
}

class NoteException(message: String) : RuntimeException(message)
class CommentException(message: String) : RuntimeException(message)
class CommentRestoreException(message: String) : RuntimeException(message)