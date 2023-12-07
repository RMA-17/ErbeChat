package data.source.remote

data class User(
    val name: String,
    val lastMessage: String
)

val users = listOf(
    User(
        "Mamang",
        "Ayo mang ke mana gek"
    ),
    User(
        "Miming",
        "Ayo ming ke mana gek"
    ),
    User(
        "Momong",
        "Ayo mang ke mana gek"
    ),
    User(
        "Memeng",
        "Ayo mang ke mana gek"
    ),
)