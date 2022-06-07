class CommentService : CrudService<Comment> {

    override fun add(comment: Comment): Comment {
        for (note in notes) {
            if (!note.deleted) {
                if (note.id == comment.noteId) {
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

    override fun delete(idComment: Int): Boolean {
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

    override fun edit(commentId: Int, newComment: Comment): Boolean {
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

    override fun getById(id: Int) {
        for (comment in comments) {
            if (id == comment.noteId) {
                println(comment.message)
            }
        }
    }

    override fun restore(commentId: Int): Boolean {
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