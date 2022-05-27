fun main() {
    val service = NoteService()
    val note1 = Note(1, 111, "note1", "text1", 11111111, deleted = true)
    val note2 = Note(2, 222, "note2", "text2", 22222222, deleted = true)
    val note3 = Note(3, 333, "note3", "text3", 33333333, deleted = true)
    val note4 = Note(4, 444, "note4", "text4", 44444444, deleted = true)
    val comment1 = Comment(1, 1, "comment 1", 1, true)
    val comment2 = Comment(2, 2, "comment 2", 2, true)
    val newNote = Note(5, 555, "newNote", "newNText", 55555555, deleted = true)
    val newComment = Comment(3, 3, "new comment", 3, true)

    service.add(note1)
    service.add(note2)
    service.add(note3)
    service.add(note4)
    service.createComment(1, comment1)
    service.createComment(4, comment2)
    service.delete(3)
    service.deleteComment(1)
    service.getTitle(2)
    service.getById(1)
    service.getComments(4)
    service.restoreComment(1)
    service.edit(1, newNote)
    service.editComment(1, newComment)
    service.deleteComment(3)

}
