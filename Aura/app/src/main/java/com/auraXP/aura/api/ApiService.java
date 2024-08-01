package com.auraXP.aura.api;

import com.auraXP.aura.api.models.Account;
import com.auraXP.aura.api.models.AccountCreate;
import com.auraXP.aura.api.models.AccountUpdateEmail;
import com.auraXP.aura.api.models.AccountUpdateUsername;
import com.auraXP.aura.api.models.Challenge;
import com.auraXP.aura.api.models.ChallengeCreate;
import com.auraXP.aura.api.models.ChallengeStatus;
import com.auraXP.aura.api.models.FriendRequest;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/")
    Call<Map<String, String>> readRoot();

    @POST("/accounts/")
    Call<Account> createAccount(@Body AccountCreate account);

    @GET("/accounts/me/")
    Call<Account> readAccountMe(@Query("email") String email);

    @GET("/accounts/")
    Call<List<Account>> readAccounts(@Query("skip") int skip, @Query("limit") int limit);

    @DELETE("/accounts/{account_id}")
    Call<Void> deleteAccount(@Path("account_id") int accountId);

    @PUT("/accounts/{account_id}/username")
    Call<Account> updateUsername(@Path("account_id") int accountId, @Body AccountUpdateUsername usernameUpdate);

    @PUT("/accounts/{account_id}/email")
    Call<Account> updateEmail(@Path("account_id") int accountId, @Body AccountUpdateEmail emailUpdate);

    @GET("/challenges/")
    Call<List<Challenge>> readChallenges(@Query("skip") int skip, @Query("limit") int limit);

    @POST("/challenges/")
    Call<Challenge> createChallenge(@Body ChallengeCreate challenge);

    @POST("/accounts/{account_id}/accept_challenge/{challenge_id}")
    Call<ChallengeStatus> acceptChallenge(@Path("account_id") int accountId, @Path("challenge_id") int challengeId);

    @POST("/accounts/{account_id}/complete_challenge/{challenge_id}")
    Call<Account> completeChallenge(@Path("account_id") int accountId, @Path("challenge_id") int challengeId);

    @POST("/accounts/{account_id}/fail_challenge/{challenge_id}")
    Call<Account> failChallenge(@Path("account_id") int accountId, @Path("challenge_id") int challengeId);

    @GET("/accounts/{account_id}/friends")
    Call<List<Account>> getFriends(@Path("account_id") int accountId);

    @GET("/accounts/search/")
    Call<List<Account>> searchAccounts(@Query("username") String username);

    @GET("/accounts/leaderboard")
    Call<List<Account>> getLeaderboard(@Query("skip") int skip, @Query("limit") int limit);

    @POST("/accounts/{sender_id}/send_friend_request/{receiver_id}")
    Call<Void> sendFriendRequest(@Path("sender_id") int senderId, @Path("receiver_id") int receiverId);

    @POST("/accounts/{sender_id}/accept_friend_request/{receiver_id}")
    Call<Void> acceptFriendRequest(@Path("sender_id") int senderId, @Path("receiver_id") int receiverId);

    @POST("/accounts/{sender_id}/reject_friend_request/{receiver_id}")
    Call<Void> rejectFriendRequest(@Path("sender_id") int senderId, @Path("receiver_id") int receiverId);

    @GET("/accounts/{account_id}/sent_friend_requests")
    Call<List<FriendRequest>> getSentFriendRequests(@Path("account_id") int accountId);

    @GET("/accounts/{account_id}/received_friend_requests")
    Call<List<FriendRequest>> getReceivedFriendRequests(@Path("account_id") int accountId);

    @POST("/predict")
    Call<GeminiResponse> processImage(
            @Part MultipartBody.Part file,
            @Part("description") RequestBody description
    );
}