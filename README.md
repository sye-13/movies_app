# 🎬 Movies App
An Android application for browsing movies and viewing their details.

## 🚀 Getting Started
The app retrieves movie data from [TMDB](https://www.themoviedb.org/). To access the API, you need an access token, which can be obtained by creating an account on TMDB.

### 🔑 Setting Up Your Access Token
1. Generate an access token from your TMDB account.
2. Add the token to the `local.properties` file at the root of your project:

   ```properties
   TMDB_ACCESS_TOKEN="your_access_token_here"
   ```  

3. Ensure the token is enclosed in **double quotes** to prevent parsing issues.
4. The `local.properties` file should **not** be committed to version control for security reasons.

Now you're ready to build and run the app! 🎉
