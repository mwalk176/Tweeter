package edu.byu.cs.tweeter.client.view.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.StatusPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.PostStatusTask;
import edu.byu.cs.tweeter.model.UserCache;
import edu.byu.cs.tweeter.model.service.request.PostStatusRequest;
import edu.byu.cs.tweeter.model.service.response.PostStatusResponse;


public class PostStatusDialog extends DialogFragment implements StatusPresenter.View, PostStatusTask.PostStatusObserver {

    EditText editStatus;
    StatusPresenter presenter;

    final PostStatusTask.PostStatusObserver observer = this;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        presenter = new StatusPresenter(this);


        final View dialogView = inflater.inflate(R.layout.fragment_post_status, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialogView)
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        editStatus = (EditText) dialogView.findViewById(R.id.editStatusField);
                        String status = editStatus.getText().toString();


                        Toast.makeText(getContext(), "Posting Status.....", Toast.LENGTH_SHORT).show();
                        PostStatusRequest request = new PostStatusRequest(status,
                                UserCache.getInstance().getCurrentlyLoggedInUser(),
                                UserCache.getInstance().getAuthToken());
                        PostStatusTask postStatusTask = new PostStatusTask(observer);
                        postStatusTask.execute(request);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //cancel
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public PostStatusDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_status, container, false);
    }

    @Override
    public void postStatusSuccessful(PostStatusResponse response) {
/*        Toast.makeText(getActivity(), "Post Successful!",
                Toast.LENGTH_SHORT).show();*/
    }

    @Override
    public void postStatusFailure(PostStatusResponse response) {
/*        if(response != null && response.getMessage() != null) {
            Toast.makeText(getActivity(), "Whoops, " + response.getMessage(),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Unexpected error occurred while trying to post status!",
                    Toast.LENGTH_SHORT).show();
        }*/
    }
}
