package com.example.gocart.auth.repositories

enum class RepoErrors {
    NoInternetConnection,
    ServerError,
    EmptyBody
}


enum class LoginErrors {
    NoInternetConnection,
    ServerError,
    CustomerNotFound,
}