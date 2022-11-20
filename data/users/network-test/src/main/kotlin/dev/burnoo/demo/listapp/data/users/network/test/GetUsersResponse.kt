package dev.burnoo.demo.listapp.data.users.network.test

fun getUsersResponse(firstUserId: String) = """
{
    "data": [
        {
            "id": "$firstUserId",
            "title": "ms",
            "firstName": "Adina",
            "lastName": "Barbosa",
            "picture": "https://randomuser.me/api/portraits/med/women/28.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109cd",
            "title": "mr",
            "firstName": "Roberto",
            "lastName": "Vega",
            "picture": "https://randomuser.me/api/portraits/med/men/25.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109ce",
            "title": "mr",
            "firstName": "Rudi",
            "lastName": "Droste 2",
            "picture": "https://randomuser.me/api/portraits/med/men/83.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109cf",
            "title": "mrs",
            "firstName": "Carolina",
            "lastName": "Lima",
            "picture": "https://randomuser.me/api/portraits/med/women/5.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109d0",
            "title": "mr",
            "firstName": "Emre 1",
            "lastName": "Asikoglu",
            "picture": "https://randomuser.me/api/portraits/med/men/23.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109d3",
            "title": "mr",
            "firstName": "Friedrich-Karl",
            "lastName": "Brand",
            "picture": "https://randomuser.me/api/portraits/med/men/7.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109d4",
            "title": "mr",
            "firstName": "Valentin",
            "lastName": "Ortega",
            "picture": "https://randomuser.me/api/portraits/med/men/3.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109d6",
            "title": "mrs",
            "firstName": "Elisa",
            "lastName": "Lorenzo",
            "picture": "https://randomuser.me/api/portraits/med/women/89.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109d8",
            "title": "mrs",
            "firstName": "Karoline",
            "lastName": "Sviggum",
            "picture": "https://randomuser.me/api/portraits/med/women/61.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109d9",
            "title": "ms",
            "firstName": "Nuria",
            "lastName": "Leon",
            "picture": "https://randomuser.me/api/portraits/med/women/93.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109db",
            "title": "miss",
            "firstName": "Naomi",
            "lastName": "Rodrigues",
            "picture": "https://randomuser.me/api/portraits/med/women/39.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109dc",
            "title": "mr",
            "firstName": "Evan",
            "lastName": "Roux",
            "picture": "https://randomuser.me/api/portraits/med/men/59.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109dd",
            "title": "mr",
            "firstName": "Miguel",
            "lastName": "Lima",
            "picture": "https://randomuser.me/api/portraits/med/men/31.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109de",
            "title": "miss",
            "firstName": "Bessie",
            "lastName": "Burke",
            "picture": "https://randomuser.me/api/portraits/med/women/72.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109df",
            "title": "mrs",
            "firstName": "Anaelle",
            "lastName": "Dumas",
            "picture": "https://randomuser.me/api/portraits/med/women/25.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109e0",
            "title": "miss",
            "firstName": "Milly",
            "lastName": "Norman",
            "picture": "https://randomuser.me/api/portraits/med/women/31.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109e1",
            "title": "mr",
            "firstName": "James",
            "lastName": "Black",
            "picture": "https://randomuser.me/api/portraits/med/men/29.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109e2",
            "title": "mr",
            "firstName": "Heinz-Georg",
            "lastName": "Fiedler",
            "picture": "https://randomuser.me/api/portraits/med/men/81.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109e3",
            "title": "mr",
            "firstName": "Vetle",
            "lastName": "Aasland",
            "picture": "https://randomuser.me/api/portraits/med/men/97.jpg"
        },
        {
            "id": "60d0fe4f5311236168a109e4",
            "title": "mr",
            "firstName": "Pwry",
            "lastName": "Shylyrd",
            "picture": "https://randomuser.me/api/portraits/med/men/37.jpg"
        }
    ],
    "total": 122,
    "page": 0,
    "limit": 20
}
""".trimIndent()
