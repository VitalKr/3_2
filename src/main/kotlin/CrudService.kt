interface CrudService<N> {

    fun add(note: N): N

    fun delete(id: Int): Boolean

    fun edit(id: Int, note: N): Boolean

    fun getById(id: Int)

    fun restore(id: Int): Boolean
}

var comments = mutableListOf<Comment>()
var notes = mutableListOf<Note>()
