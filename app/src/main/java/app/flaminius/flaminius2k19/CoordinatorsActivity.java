
package app.flaminius.flaminius2k19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.blox.graphview.BaseGraphAdapter;
import de.blox.graphview.Graph;
import de.blox.graphview.GraphView;
import de.blox.graphview.Node;
import de.blox.graphview.ViewHolder;
import de.blox.graphview.tree.BuchheimWalkerAlgorithm;
import de.blox.graphview.tree.BuchheimWalkerConfiguration;

public class CoordinatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinators);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GraphView graphView = findViewById(R.id.graph);

        CoordinatorAdapter adapter = new CoordinatorAdapter(getCoordinators());

        graphView.setAdapter(adapter);

        BuchheimWalkerConfiguration configuration = new BuchheimWalkerConfiguration.Builder()
                .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_LEFT_RIGHT)
                .build();
        adapter.setAlgorithm(new BuchheimWalkerAlgorithm(configuration));
    }

    private Graph getCoordinators() {
        Graph graph = new Graph();
        Node president = new Node(new Coordinator(R.drawable.ob_president, "Vignesh", "President", "7904013122"));
        Node vicePresident = new Node(new Coordinator(R.drawable.ob_president_vice, "Rishi", "Vice President", "7338992387"));
        Node secretary = new Node(new Coordinator(R.drawable.ob_secretary, "Arun Kumar", "Secretary", "6380667500"));
        Node jointSecretary = new Node(new Coordinator(R.drawable.ob_secretary_joint, "Divya Dharshini", "Joint Secretary", null));
        Node coordinator = new Node(new Coordinator(R.drawable.ob_coordinator, "Ajith Kumar", "Event Coordinator", "9840543412"));
        Node jointCoordinator = new Node(new Coordinator(R.drawable.ob_coordinator_joint, "Avinash", "Joint Event Coordinator", "8939069993"));
        Node treasurer = new Node(new Coordinator(R.drawable.ob_treasurer, "Vishnu Priya", "Treasurer", null));
        Node joinTreasurer = new Node(new Coordinator(R.drawable.ob_treasurer_joint, "Saravanan", "Joint Treasurer", "9600196579"));

        graph.addEdge(president, secretary);
        graph.addEdge(secretary, jointSecretary);

        graph.addEdge(president, coordinator);
        graph.addEdge(coordinator, jointCoordinator);

        graph.addEdge(president, vicePresident);

        graph.addEdge(president, treasurer);
        graph.addEdge(treasurer, joinTreasurer);

        return graph;
    }

    static class CoordinatorAdapter extends BaseGraphAdapter<CoordinatorViewHolder> {
        CoordinatorAdapter(Graph graph) {
            super(graph);
        }

        @NonNull
        @Override
        public CoordinatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_node, parent, false);
            return new CoordinatorViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CoordinatorViewHolder viewHolder, Object data, int position) {
            viewHolder.bind((Coordinator) data);
        }
    }

    static class CoordinatorViewHolder extends ViewHolder {
        final ImageView image;
        final TextView name;
        final TextView designation;
        Coordinator coordinator;

        CoordinatorViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            designation = itemView.findViewById(R.id.designation);

            itemView.setOnClickListener((v) -> {
                if (coordinator == null || TextUtils.isEmpty(coordinator.phone)) return;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + coordinator.phone));
                v.getContext().startActivity(intent);
            });
        }

        void bind(Coordinator coordinator) {
            this.coordinator = coordinator;
            this.image.setImageResource(coordinator.image);
            this.name.setText(coordinator.name);
            this.designation.setText(coordinator.designation);
        }
    }

    static class Coordinator {
        final @DrawableRes
        int image;
        final String name;
        final String designation;
        final String phone;

        Coordinator(int image, String name, String designation, String phone) {
            this.image = image;
            this.name = name;
            this.designation = designation;
            this.phone = phone;
        }
    }
}
