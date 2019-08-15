package app.flaminius.flaminius2k19.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import app.flaminius.flaminius2k19.R;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterTask extends AsyncTask<RequestBody, Void, JSONObject> {
    private static final String URL = "https://script.google.com/macros/s/AKfycbybm4JNQcwQJNuwnjiIfJzFBv3Uxizj1zrbGqtDuUrmZf3FPmVn/exec";

    private OnCompletionListener onCompletionListener;
    private FormBody.Builder formBodyBuilder = new FormBody.Builder();

    public RegisterTask setName(@NonNull String name) {
        formBodyBuilder.add("Name", name);
        return this;
    }

    public RegisterTask setEmail(@NonNull String email) {
        formBodyBuilder.add("Email", email);
        return this;
    }

    public RegisterTask setPhone(@NonNull String phone) {
        formBodyBuilder.add("Phone", phone);
        return this;
    }

    public RegisterTask setCollege(@NonNull String college) {
        formBodyBuilder.add("College", college);
        return this;
    }

    public RegisterTask setDepartment(@NonNull String department) {
        formBodyBuilder.add("Department", department);
        return this;
    }

    public RegisterTask setFoodPreference(@NonNull String foodPreference) {
        formBodyBuilder.add("Food Preference", foodPreference);
        return this;
    }

    public RegisterTask setEvent(@NonNull String event) {
        formBodyBuilder.add("Event", event);
        return this;
    }

    public void register(Context context, @NonNull OnCompletionListener onCompletionListener) {
        this.onCompletionListener = onCompletionListener;

        boolean sendMail = context.getResources().getBoolean(R.bool.sendMail);
        formBodyBuilder.add("SendMail", String.valueOf(sendMail));
        execute(formBodyBuilder.build());
    }

    @Override
    protected JSONObject doInBackground(RequestBody... requestBodies) {
        RequestBody requestData = requestBodies[0];

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(new Request.Builder()
                .url(URL)
                .post(requestData)
                .build());
        try {
            Response response = call.execute();
            String a = response.body().string();
            Log.d("RegisterTask", "doInBackground: " + a);
            return new JSONObject(a);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject responseData) {
        if (responseData == null) {
            onCompletionListener.onError(new Exception("Null Response"));
            return;
        }
        try {
            if (responseData.getString("result").equals("success")) {
                onCompletionListener.onSuccess();
            } else {
                String error = responseData.getString("error");
                Exception exception;
                if (error.equals("email_already_exists")) {
                    exception = new EmailAlreadyExistException("Email already registered");
                } else if (error.equals("phone_already_exists")) {
                    exception = new PhoneAlreadyExistsException("Phone number already registered");
                } else {
                    exception = new Exception("Registration Failed!");
                }
                onCompletionListener.onError(exception);
            }
        } catch (Exception e) {
            e.printStackTrace();
            onCompletionListener.onError(e);
        }
    }

    public interface OnCompletionListener {
        void onSuccess();

        void onError(Exception e);
    }

    public static class EmailAlreadyExistException extends Exception {
        EmailAlreadyExistException(String msg) {
            super(msg);
        }
    }

    public static class PhoneAlreadyExistsException extends Exception {
        PhoneAlreadyExistsException(String msg) {
            super(msg);
        }
    }
}
