package com.example.studyproject.models

import java.io.Serializable

data class PersonalDataModel(
    var name: String,
    var secondName: String,
    var age: Int,
    var phoneNumber: String
) : Serializable {

    constructor(
        name: String,
        secondName: String
    ) : this(name, secondName, 0, "")
}
