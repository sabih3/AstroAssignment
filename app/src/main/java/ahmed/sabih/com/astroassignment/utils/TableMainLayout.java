package ahmed.sabih.com.astroassignment.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ahmed.sabih.com.astroassignment.R;
import ahmed.sabih.com.astroassignment.models.EventsResponse;

/**
 * Created by sabih on 03-Nov-17.
 */


public class TableMainLayout extends RelativeLayout {

    private static final int WIDTH_TIME_SLOT = 600;
    public final String TAG = "TableMainLayout.java";
    private final List<EventsResponse.Getevent> eventsList;


    private TableLayout channelNamesTable;
    private TableLayout timeSlotsTable;
    private TableLayout channelListTable;
    private TableLayout eventsTable;

    private HorizontalScrollView horizontalScrollTimeSlot;
    private HorizontalScrollView horizontalScrollEvents;

    private ScrollView scrollViewChannelList;
    private ScrollView scrollViewEvents;

    private Context context;

    private List<SampleObject> sampleObjects = SampleObject.getSampleObjects();

    int headerCellsWidth[] = new int[SampleObject.getTimeSlots().size()+1];

    public TableMainLayout(Context context, List<EventsResponse.Getevent> eventsList) {

        super(context);

        this.context = context;
        this.eventsList = eventsList;

        // initialize the main components (TableLayouts, HorizontalScrollView, ScrollView)
        this.initComponents();
        this.setComponentsId();
        this.setScrollViewAndHorizontalScrollViewTag();


        // no need to assemble component A, since it is just a table
        this.horizontalScrollTimeSlot.addView(this.timeSlotsTable);

        this.scrollViewChannelList.addView(this.channelListTable);

        this.scrollViewEvents.addView(this.horizontalScrollEvents);
        this.horizontalScrollEvents.addView(this.eventsTable);

        // add the components to be part of the main layout
        this.addComponentToMainLayout();



        // add some table rows
        this.addChannelListHeaderRow();
        this.addRowsInTimeSlotsTable();

        this.resizeHeaderHeight();

        this.getTableRowHeaderCellWidth();

        this.setChannelAndEvents(eventsList);

        this.resizeBodyTableRowHeight();
    }

    // initalized components
    private void initComponents(){

        this.channelNamesTable = new TableLayout(this.context);
        this.timeSlotsTable = new TableLayout(this.context);
        this.channelListTable = new TableLayout(this.context);
        this.eventsTable = new TableLayout(this.context);

        this.horizontalScrollTimeSlot = new MyHorizontalScrollView(this.context);
        this.horizontalScrollEvents = new MyHorizontalScrollView(this.context);

        this.scrollViewChannelList = new MyScrollView(this.context);
        this.scrollViewEvents = new MyScrollView(this.context);

        this.channelNamesTable.setBackgroundColor(Color.GREEN);
        this.horizontalScrollTimeSlot.setBackgroundColor(Color.LTGRAY);

    }

    // set essential component IDs
    private void setComponentsId(){
        this.channelNamesTable.setId(1);
        this.horizontalScrollTimeSlot.setId(2);
        this.scrollViewChannelList.setId(3);
        this.scrollViewEvents.setId(4);
    }

    // set tags for some horizontal and vertical scroll view
    private void setScrollViewAndHorizontalScrollViewTag(){

        this.horizontalScrollTimeSlot.setTag("horizontal scroll view b");
        this.horizontalScrollEvents.setTag("horizontal scroll view d");

        this.scrollViewChannelList.setTag("scroll view c");
        this.scrollViewEvents.setTag("scroll view d");
    }

    // we add the components here in our TableMainLayout
    private void addComponentToMainLayout(){

        // RelativeLayout params were very useful here
        // the addRule method is the key to arrange the components properly
        RelativeLayout.LayoutParams componentB_Params =
                new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        componentB_Params.addRule(RelativeLayout.RIGHT_OF, this.channelNamesTable.getId());

        RelativeLayout.LayoutParams componentC_Params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        componentC_Params.addRule(RelativeLayout.BELOW, this.channelNamesTable.getId());

        RelativeLayout.LayoutParams componentD_Params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        componentD_Params.addRule(RelativeLayout.RIGHT_OF, this.scrollViewChannelList.getId());
        componentD_Params.addRule(RelativeLayout.BELOW, this.horizontalScrollTimeSlot.getId());

        // 'this' is a relative layout,
        // we extend this table layout as relative layout as seen during the creation of this class
        this.addView(this.channelNamesTable);
        this.addView(this.horizontalScrollTimeSlot, componentB_Params);
        this.addView(this.scrollViewChannelList, componentC_Params);
        this.addView(this.scrollViewEvents, componentD_Params);

    }



    private void addChannelListHeaderRow(){
        this.channelNamesTable.addView(getChannelListHeaderRow());
    }

    private void addRowsInTimeSlotsTable(){
        this.timeSlotsTable.addView(getTimeSlotRows());
    }

    // generate table row of table A
    private TableRow getChannelListHeaderRow(){

        TableRow componentATableRow = new TableRow(this.context);
        TextView textView = this.headerTextView("Channels");
        componentATableRow.addView(textView);

        return componentATableRow;
    }

    // generate table row of table B
    private TableRow getTimeSlotRows(){
        ArrayList<String> timeSlots = SampleObject.getTimeSlots();
        TableRow componentBTableRow = new TableRow(this.context);


        TableRow.LayoutParams params = new TableRow.LayoutParams(WIDTH_TIME_SLOT,LayoutParams.MATCH_PARENT);
        params.setMargins(2, 0, 0, 0);

        for(String timeSlot :timeSlots){
            TextView textView = this.headerTextView(timeSlot);
            textView.setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
            textView.setLayoutParams(params);
            componentBTableRow.addView(textView);
        }

        return componentBTableRow;
    }

    // generate table row of table C and table D
    public void setChannelAndEvents(List<EventsResponse.Getevent> eventsList){

        // just seeing some header cell width
        for(int x=0; x<this.headerCellsWidth.length; x++){
            Log.v("TableMainLayout.java", this.headerCellsWidth[x]+"");
        }

        for(SampleObject sampleObject : this.sampleObjects){

            TableRow channelNameRow = this.getChannelNameRows("HBO");
            TableRow eventRow = this.getEventsRows(this.eventsList);


            this.channelListTable.addView(channelNameRow);
            this.eventsTable.addView(eventRow);

        }
    }

    //gives channel names rows
    private TableRow getChannelNameRows(String channelName){

        TableRow.LayoutParams params = new TableRow.LayoutParams( this.headerCellsWidth[0],LayoutParams.MATCH_PARENT);
        params.setMargins(0, 2, 0, 0);

        TableRow channelNameRow = new TableRow(this.context);
        TextView textView = this.bodyTextView(channelName);
        channelNameRow.addView(textView,params);

        return channelNameRow;
    }

    private TableRow getEventsRows(List<EventsResponse.Getevent> eventList){



        TableRow taleRowForTableD = new TableRow(this.context);

        for(EventsResponse.Getevent event : eventList){
            String displayDuration = event.getDisplayDuration();
            String[] tokens = displayDuration.split(":");
            int hrs = Integer.parseInt(tokens[0]);
            int mins = Integer.parseInt(tokens[1]);
            int secs = Integer.parseInt(tokens[2]);

            int durationInMins = (60*hrs)+mins;
            int widthOfEvent = durationInMins * 10;

            TableRow.LayoutParams params = new TableRow.LayoutParams( widthOfEvent,LayoutParams.MATCH_PARENT);
            params.setMargins(2, 2, 0, 0);

            TextView textViewB = this.bodyTextView(event.getProgrammeTitle());
            textViewB.setBackground(ContextCompat.getDrawable(context, R.drawable.grey_box_background));
            textViewB.setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
            taleRowForTableD.addView(textViewB,params);
        }

        return taleRowForTableD;

    }

    // table cell standard TextView
    private TextView bodyTextView(String label){

        TextView bodyTextView = new TextView(this.context);
        bodyTextView.setBackgroundColor(Color.WHITE);
        bodyTextView.setText(label);
        bodyTextView.setGravity(Gravity.CENTER);
        bodyTextView.setPadding(5, 5, 5, 5);

        return bodyTextView;
    }

    // header standard TextView
    private TextView headerTextView(String label){

        TextView headerTextView = new TextView(this.context);
        headerTextView.setBackgroundColor(Color.WHITE);
        headerTextView.setText(label);
        headerTextView.setGravity(Gravity.CENTER);
        headerTextView.setPadding(5, 5, 5, 5);

        return headerTextView;
    }

    // resizing TableRow height starts here
    private void resizeHeaderHeight() {

        TableRow productNameHeaderTableRow = (TableRow) this.channelNamesTable.getChildAt(0);
        TableRow productInfoTableRow = (TableRow)  this.timeSlotsTable.getChildAt(0);

        int rowAHeight = this.viewHeight(productNameHeaderTableRow);
        int rowBHeight = this.viewHeight(productInfoTableRow);

        TableRow tableRow = rowAHeight < rowBHeight ? productNameHeaderTableRow : productInfoTableRow;
        int finalHeight = rowAHeight > rowBHeight ? rowAHeight : rowBHeight;

        this.matchLayoutHeight(tableRow, finalHeight);
    }

    private void getTableRowHeaderCellWidth(){

        int tableAChildCount = ((TableRow)this.channelNamesTable.getChildAt(0)).getChildCount();
        int tableBChildCount = ((TableRow)this.timeSlotsTable.getChildAt(0)).getChildCount();;

        for(int x=0; x<(tableAChildCount+tableBChildCount); x++){

            if(x==0){
                this.headerCellsWidth[x] = this.viewWidth(((TableRow)this.channelNamesTable.getChildAt(0)).getChildAt(x));
            }else{
                this.headerCellsWidth[x] = this.viewWidth(((TableRow)this.timeSlotsTable.getChildAt(0)).getChildAt(x-1));
            }

        }
    }

    // resize body table row height
    private void resizeBodyTableRowHeight(){

        int tableC_ChildCount = this.channelListTable.getChildCount();

        for(int x=0; x<tableC_ChildCount; x++){

            TableRow productNameHeaderTableRow = (TableRow) this.channelListTable.getChildAt(x);
            TableRow productInfoTableRow = (TableRow)  this.eventsTable.getChildAt(x);

            int rowAHeight = this.viewHeight(productNameHeaderTableRow);
            int rowBHeight = this.viewHeight(productInfoTableRow);

            TableRow tableRow = rowAHeight < rowBHeight ? productNameHeaderTableRow : productInfoTableRow;
            int finalHeight = rowAHeight > rowBHeight ? rowAHeight : rowBHeight;

            this.matchLayoutHeight(tableRow, finalHeight);
        }

    }

    // match all height in a table row
    // to make a standard TableRow height
    private void matchLayoutHeight(TableRow tableRow, int height) {

        int tableRowChildCount = tableRow.getChildCount();

        // if a TableRow has only 1 child
        if(tableRow.getChildCount()==1){

            View view = tableRow.getChildAt(0);
            TableRow.LayoutParams params = (TableRow.LayoutParams) view.getLayoutParams();
            params.height = height - (params.bottomMargin + params.topMargin);

            return ;
        }

        // if a TableRow has more than 1 child
        for (int x = 0; x < tableRowChildCount; x++) {

            View view = tableRow.getChildAt(x);

            TableRow.LayoutParams params = (TableRow.LayoutParams) view.getLayoutParams();

            if (!isTheHeighestLayout(tableRow, x)) {
                params.height = height - (params.bottomMargin + params.topMargin);
                return;
            }
        }

    }

    // check if the view has the highest height in a TableRow
    private boolean isTheHeighestLayout(TableRow tableRow, int layoutPosition) {

        int tableRowChildCount = tableRow.getChildCount();
        int heighestViewPosition = -1;
        int viewHeight = 0;

        for (int x = 0; x < tableRowChildCount; x++) {
            View view = tableRow.getChildAt(x);
            int height = this.viewHeight(view);

            if (viewHeight < height) {
                heighestViewPosition = x;
                viewHeight = height;
            }
        }

        return heighestViewPosition == layoutPosition;
    }

    // read a view's height
    private int viewHeight(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return view.getMeasuredHeight();
    }

    // read a view's width
    private int viewWidth(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        return view.getMeasuredWidth();
    }

    // horizontal scroll view custom class
    class MyHorizontalScrollView extends HorizontalScrollView{

        public MyHorizontalScrollView(Context context) {
            super(context);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {
            String tag = (String) this.getTag();

            if(tag.equalsIgnoreCase("horizontal scroll view b")){
                horizontalScrollEvents.scrollTo(l, 0);
            }else{
                horizontalScrollTimeSlot.scrollTo(l, 0);
            }
        }

    }

    // scroll view custom class
    class MyScrollView extends ScrollView{

        public MyScrollView(Context context) {
            super(context);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {

            String tag = (String) this.getTag();

            if(tag.equalsIgnoreCase("scroll view c")){
                scrollViewEvents.scrollTo(0, t);
            }else{
                scrollViewChannelList.scrollTo(0,t);
            }
        }
    }


}
