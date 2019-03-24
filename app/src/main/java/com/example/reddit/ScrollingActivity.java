package com.example.reddit;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.Subreddit;
import net.dean.jraw.models.SubredditSort;
import net.dean.jraw.models.TimePeriod;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthException;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.oauth.StatefulAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;
import net.dean.jraw.references.SubredditReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ScrollingActivity extends AppCompatActivity {
    private RecyclerView view;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Post> getPosts(final List<Post> posts, final String subreddits[]) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    Credentials ou = Credentials.userlessApp("s6pjSsc3TuGToA", UUID.randomUUID());
                    UserAgent userAgent = new UserAgent("userless_app", "com.example.reddit", "v0.1", "monodll");
                    NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
                    RedditClient reddit = OAuthHelper.automatic(adapter, ou);
                    SubredditReference subs = reddit.subreddits(subreddits[0], subreddits[1], subreddits[2], subreddits[3], subreddits[4]);
                    DefaultPaginator<Submission> list = subs.posts()
                            .sorting(SubredditSort.TOP)
                            .timePeriod(TimePeriod.DAY)
                            .limit(50)
                            .build();
                    Listing<Submission> submissions = list.next();
                    for (Submission s : submissions) {
                        System.out.println(s.getPostHint());
                        if (s.getPostHint().equals("image")) {
                            posts.add(new Post(s.getTitle(), s.getSelfText(), s.getUrl(), s.getSubreddit(), s.getAuthor()));
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            thread.start();
            thread.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final List<Post> posts = new ArrayList<>();


        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Reddit");
        setSupportActionBar(toolbar);

        final String memeSubs[] = {"okbuddyretard", "comedyheaven", "deepfriedmemes", "funny", "dankmemes"};
        final String picSubs[] = {"pics", "oldschoolcool", "crappydesign", "nocontextpics", "earthporn"};
        final String awwSubs[] = {"aww", "rarepuppers", "cattaps", "thecatdimension", "AnimalPics"};
        view = (RecyclerView) findViewById(R.id.rec);
        layoutManager = new LinearLayoutManager(this);
        view.setLayoutManager(layoutManager);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vieww) {
                posts.clear();
                getPosts(posts, memeSubs);
                mAdapter = new MyAdapter(posts);
                view.setAdapter(mAdapter);
            }
        });
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vieww) {
                posts.clear();
                getPosts(posts, picSubs);
                mAdapter = new MyAdapter(posts);
                view.setAdapter(mAdapter);
            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vieww) {
                posts.clear();
                getPosts(posts, awwSubs);
                mAdapter = new MyAdapter(posts);
                view.setAdapter(mAdapter);
            }
        });

        mAdapter = new MyAdapter(posts);
        view.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
