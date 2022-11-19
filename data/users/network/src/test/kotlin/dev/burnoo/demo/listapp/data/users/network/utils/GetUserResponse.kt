package dev.burnoo.demo.listapp.data.users.network.utils

fun getUserResponse(firstName: String, lastName: String) = """
{
    "id": "60d0fe4f5311236168a109cc",
    "title": "ms",
    "firstName": "$firstName",
    "lastName": "$lastName",
    "picture": "https://randomuser.me/api/portraits/med/women/28.jpg",
    "gender": "female",
    "email": "edina.barbosa@example.com",
    "dateOfBirth": "1952-09-03T13:27:29.424Z",
    "phone": "(64) 5796-9260",
    "location": {
        "street": "8750, Rua Carlos Gomes",
        "city": "Recife",
        "state": "CearÃ¡",
        "country": "Brazil",
        "timezone": "+1:00"
    },
    "registerDate": "2021-06-21T21:02:07.719Z",
    "updatedDate": "2021-06-21T21:02:07.719Z"
}
""".trimIndent()
