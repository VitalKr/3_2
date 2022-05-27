data class Comment(
    var noteId: Int,
    var ownerId: Int,
    val message: String,
    var commentId: Int,
    var deleted: Boolean
) {
}