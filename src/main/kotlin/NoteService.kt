class NoteService {
    var notes = mutableListOf<Note>()
    var comments = mutableListOf<Comment>()

    fun add(note: Note): Note {
        val newNote = note.copy(id = notes.size + 1, deleted = false)
        notes.add(newNote)
        println(notes.last())
        return notes.last()
    }

    fun createComment(id: Int, comment: Comment): Comment {
        for (note in notes) {
            if (!note.deleted) {
                if (id == note.id) {
                    val newComment = comment.copy(
                        noteId = note.id, commentId = comments.size + 1,
                        message = comment.message, deleted = false
                    )
                    comments.add(newComment)
                    println(comments.last())
                    return comments.last()
                }
            }
        }
        throw NoteException("Такой заметки нет")
    }

    fun delete(id: Int): Boolean {
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

    fun deleteComment(idComment: Int): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (!comment.deleted) {
                if (idComment == comment.commentId) {
                    comments[index] = comment.copy(commentId = comment.commentId, deleted = true)
                    println("Комментарий ${comment.commentId} удален.")
                    return true
                }
            }
        }
        throw CommentException("Такого комментария нет")
    }

    fun edit(id: Int, newNote: Note): Boolean {
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

    fun editComment(commentId: Int, newComment: Comment): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (!comment.deleted) {
                if (commentId == comment.commentId) {
                    comments[index] = comment.copy(
                        noteId = comment.noteId, commentId = comment.commentId,
                        deleted = false, message = newComment.message
                    )
                    println(comments[index])
                    return true
                }
            }
        }
        throw CommentException("Такого комментария нет")
    }

    fun getTitle(id: Int) {
        for (note in notes) {
            if (id == note.id) {
                if (!note.deleted) {
                    println(note.title)
                }
            }
        }
    }

    fun getById(id: Int) {
        for (note in notes) {
            if (id == note.id) {
                if (!note.deleted) {
                    println("title: ${note.title}, text: ${note.text}")
                }
            }
        }
    }

    fun getComments(id: Int) {
        for (comment in comments) {
            if (id == comment.noteId) {
                println(comment.message)
            }
        }
    }

    fun restoreComment(commentId: Int): Boolean {
        for ((index, comment) in comments.withIndex()) {
            if (commentId == comment.commentId) {
                if (comment.deleted) {
                    comments[index] = comment.copy(commentId = comment.commentId, deleted = false)
                    println("Комментарий ${comment.commentId} восстановлен.")
                    return true
                }
            }
        }
        throw CommentRestoreException("Такого комментария нет")
    }
}

class NoteException(message: String) : RuntimeException(message)
class CommentException(message: String) : RuntimeException(message)
class CommentRestoreException(message: String) : RuntimeException(message)