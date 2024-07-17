import com.example.myapplication.features.registerScreen.domain.data.model.RegisterPostBody
import com.example.myapplication.features.registerScreen.domain.data.model.RegisterResponse
import com.example.myapplication.sharedComponents.api.RegisterAPIService

class RegisterRepository(private val api: RegisterAPIService) {

    suspend fun register(userRegisterPostBody: RegisterPostBody): Result<RegisterResponse> {
        val response = api.registerUser(userRegisterPostBody)
        return if (response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            } ?: Result.failure(Throwable("ERROR registering!"))
        } else {
            Result.failure(Throwable("Service FAILED!"))
        }
    }
}