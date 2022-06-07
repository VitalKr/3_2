import org.junit.Assert.assertEquals
import org.junit.Test


class NoteServiceTest {
    private val service = NoteService()
    private val serviceComment = CommentService()

    @Test
    fun testAdd() {

        //Arrange
        val noteTest = Note(0, 0, " ", " ", 0, false)
        //Act
        val add = service.add((noteTest))
        val result = add.id
        val excepted = 3
        //Assert
        assertEquals(result, excepted)
    }

    @Test
    fun testCreateComment() {
        //Arrange
        val noteTest = Note(1, 1, " ", " ", 0, false)

        val commentTest = Comment(1, 0, " ", 0, false)
        //Act
        service.add(noteTest)
        val result = serviceComment.add(commentTest)
        val excepted = 1
        //Assert
        assertEquals(result.noteId, excepted)
    }

    //
    @Test
    fun testDelete() {
        //Arrange

        //Act
        val result = service.delete(1)
        val expected = true
        //Assert
        assertEquals(result, expected)
    }

    @Test
    fun testRestoreNote() {
        //Arrange
        val noteTest = Note(1, 0, " ", " ", 0, false)
        //Act
        service.add(noteTest)
        service.delete(1)
        val result = service.restore(1)
        val expected = true
        //Assert
        assertEquals(result, expected)


    }

    @Test
    fun testDeleteComment() {
        //Arrange
        val noteTest = Note(0, 0, " ", " ", 0, false)
        service.add(noteTest)
        val commentTest = Comment(1, 0, " ", 0, false)
        serviceComment.add(commentTest)
        //Act
        val result = serviceComment.delete(1)
        val expected = true
        //Assert
        assertEquals(result, expected)
    }

    @Test
    fun testEdit() {
        //Arrange
        val noteTest = Note(1, 0, " ", "Hello", 0, false)
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
        val noteTest = Note(1, 0, "", "", 0, false)
        service.add(noteTest)
        val commentTest = Comment(1, 0, "", 0, true)
        serviceComment.add(commentTest)
        //Act
        val result = serviceComment.edit(1, commentTest)

        val expected = true
        //Assert
        assertEquals(result, expected)
    }


    @Test
    fun testGetById() {
        //Arrange

        //Act
        val result = service.getById(10)
        val expected = println("""title: title, text: text""")
        //Assert
        assertEquals(result, expected)
    }

    @Test
    fun testGetByIdComments() {
        //Arrange

        val comment = Comment(1, 0, "Hello", 0, true)
        serviceComment.add(comment)
        val result = serviceComment.add(comment)
        //Act
        val expected = "Hello"
        //Assert
        assertEquals(result.message, expected)
    }

    @Test
    fun testRestoreComment() {
        //Arrange
        val commentTest = Comment(1, 0, "", 1, true)
        //Act
        serviceComment.restore(1)
        val result = true
        //Assert
        assertEquals(commentTest.deleted, result)
    }

    @Test(expected = NoteException::class)
    fun testExceptionEditNote() {
        //Arrange
        val noteTest = Note(1, 0, " ", " ", 0, false)
        service.add(noteTest)
        //Act
        val result = service.edit(10, noteTest)
        val expected = NoteException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = NoteException::class)
    fun testExceptionDeleteNote() {
        //Act
        val result = service.delete(10)
        val expected = NoteException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = NoteException::class)
    fun testExceptionAddComment() {
        //Arrange
        val comment = Comment(0, 0, " ", 0, true)
        //Act
        val result = serviceComment.add(comment)
        val expected = NoteException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = CommentException::class)
    fun testExceptionDeleteComment() {
        //Act
        val result = serviceComment.delete(5)
        val expected = CommentException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = CommentException::class)
    fun testExceptionEditComment() {
        //Arrange
        val commentTest = Comment(0, 0, " ", 0, true)
        //Act
        val result = serviceComment.edit(5, commentTest)
        val expected = CommentException::class
        //Assert
        assertEquals(result, expected)
    }

    @Test(expected = CommentRestoreException::class)
    fun testExceptionRestoreComment() {
        //Arrange
        val commentTest = Comment(1, 0, " ", 1, true)
        commentTest.commentId = 1
        commentTest.deleted = true
        //Act
        val result = serviceComment.restore(5)
        val expected = CommentRestoreException::class
        //Assert
        assertEquals(result, expected)
    }
}






