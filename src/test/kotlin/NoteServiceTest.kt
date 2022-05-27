import org.junit.Assert.assertEquals
import org.junit.Test


class NoteServiceTest {
    val service = NoteService()

    @Test
    fun testAdd() {
        //Arrange
        val noteTest: Note = Note(1, 0, " ", " ", 0, false)
        //Act
        val add = service.add((noteTest))
        val result = add.id
        val excepted = 1
        //Assert
        assertEquals(result, excepted)
    }

    @Test
    fun testCreateComment() {
        //Arrange
        val noteTest: Note = Note(1, 1, " ", " ", 0, false)

        val commentTest: Comment = Comment(1, 0, " ", 0, false)
        //Act
        service.add((noteTest))
        val result = service.createComment(1, commentTest)
        val excepted = 1
        //Assert
        assertEquals(result.noteId, excepted)
    }

    @Test
    fun testDelete() {
        //Arrange
        val noteTest: Note = Note(1, 0, " ", " ", 0, false)
        service.add(noteTest)
        //Act
        val result = service.delete(1)
        val expected = true
        //Assert
        assertEquals(result, expected)
    }

    @Test
    fun testDeleteComment() {
        //Arrange
        val noteTest: Note = Note(0, 0, " ", " ", 0, false)
        service.add(noteTest)
        val commentTest: Comment = Comment(0, 0, " ", 0, false)
        service.createComment(1, commentTest)
        //Act
        val result = service.deleteComment(1)
        val expected = true
        //Assert
        assertEquals(result, expected)
    }

    @Test
    fun testEdit() {
        //Arrange
        val noteTest: Note = Note(1, 0, " ", "Hello", 0, false)
        service.add(noteTest)
        //Act
        val result = service.edit(1, noteTest)
        val expected = true
        //Assert
        assertEquals(result, expected)
    }

    @Test
    fun testEditComment() {
        //Arrange
        val noteTest: Note = Note(1, 0, "", "", 0, false)
        service.add(noteTest)
        val commentTest: Comment = Comment(1, 0, "", 0, true)
        service.createComment(1, commentTest)
        //Act
        val result = service.editComment(1, commentTest)

        val expected = true
        //Assert
        assertEquals(result, expected)
    }

    @Test
    fun testGetTitle() {
        //Arrange
        val noteTest: Note = Note(10, 0, "title", "", 0, false)
        val result = service.add(noteTest)
        //Act
        val expected = "title"
        //Assert
        assertEquals(result.title, expected)
    }

    @Test
    fun testGetById() {
        //Arrange
        val note: Note = Note(10, 0, "title", "text", 0, false)
        //Act
        val result = service.getById(10)
        val expected = println("""title: title, text: text""")
        //Assert
        assertEquals(result, expected)
    }

    @Test
    fun testGetComments() {
        //Arrange
        val note: Note = Note(0, 0, "", "", 0, false)
        val comment: Comment = Comment(0, 0, "Hello", 0, true)
        service.add(note)
        val result = service.createComment(1, comment)
        //Act
        val expected = "Hello"
        //Assert
        assertEquals(result.message, expected)
    }

    @Test
    fun testRestoreComment() {
        //Arrange
        val commentTest: Comment = Comment(1, 0, "", 1, true)
        val noteTest: Note = Note(1, 0, " ", " ", 0, false)
        //Act
        service.add(noteTest)
        service.createComment(1, commentTest)
        service.deleteComment(1)
        service.restoreComment(1)
        val result = true
        //Assert
        assertEquals(commentTest.deleted, result)
    }

    @Test(expected = NoteException::class)
    fun testExceptionEditNote() {
        //Arrange
        val noteTest: Note = Note(1, 0, " ", " ", 0, false)
        service.add(noteTest)
        //Act
        val result = service.edit(5, noteTest)
        val expected = NoteException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = NoteException::class)
    fun testExceptionDeleteNote() {
        //Act
        val result = service.delete(5)
        val expected = NoteException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = NoteException::class)
    fun testExceptionAddComment() {
        //Arrange
        val comment: Comment = Comment(0, 0, " ", 0, true)
        //Act
        val result = service.createComment(6, comment)
        val expected = NoteException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = CommentException::class)
    fun testExceptionDeleteComment() {
        //Act
        val result = service.deleteComment(5)
        val expected = CommentException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = CommentException::class)
    fun testExceptionEditComment() {
        //Arrange
        val commentTest: Comment = Comment(0, 0, " ", 0, true)
        //Act
        val result = service.editComment(5, commentTest)
        val expected = CommentException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = CommentRestoreException::class)
    fun testExceptionRestoreComment() {
        //Arrange
        val commentTest: Comment = Comment(1, 0, " ", 1, true)
        commentTest.commentId = 1
        commentTest.deleted = true
        //Act
        val result = service.restoreComment(5)
        val expected = CommentRestoreException::class
        //Assert
        assertEquals(result, expected)
    }
}






