package dev.burnoo.demo.listapp.data.users.network.test

fun getUserResponse() = """
{
    "id": "${TestApiUser.id}",
    "title": "${TestApiUser.title}",
    "firstName": "${TestApiUser.firstName}",
    "lastName": "${TestApiUser.lastName}",
    "picture": "${TestApiUser.photoUrl}",
    "gender": "${TestApiUser.gender}",
    "email": "${TestApiUser.email}",
    "dateOfBirth": "1952-09-03T13:27:29.424Z",
    "phone": "${TestApiUser.phone}",
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
