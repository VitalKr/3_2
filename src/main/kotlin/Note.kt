data class Note(
    var id: Int,
    var ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    var deleted: Boolean
) {
}