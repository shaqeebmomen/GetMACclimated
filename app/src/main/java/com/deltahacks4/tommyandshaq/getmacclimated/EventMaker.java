package com.deltahacks4.tommyandshaq.getmacclimated;

import com.deltahacks4.tommyandshaq.getmacclimated.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tommy on 2018-01-28.
 */

public class EventMaker {
    Event event1= new Event("DeltaHacks IV Closing Ceremony","Sunday, January 28th", "5:00 PM", "The closing ceremonies of DeltaHacksIV will be the end of an amazing hackathon", "MDCL", "Open", "https://deltahacks.com/schedule/latest.pdf", (float) 43.261148, (float) -79.91694);
    Event event2 = new Event("Professional Development Conference","Saturday, February 3rd","10 AM - 6 PM"," MES' upcoming Professional Development Conference, a day long conference that will help you gain skills and knowledge to help you in your job search! Start the day off with talks on networking, leadership and inclusion in the workplace. Take part in an interactive interviewing activity and end the day off with a networking dinner.","John Hodgins Engineering Building (JHE)","","https://www.eventbrite.ca/e/mes-professional-development-conference-2018-tickets-42438722361?aff=eac2", (float)43.260859, (float)-79.920410);
    Event event3 = new Event("Midterm Season Begins","Monday, February 5th","All-Day","It's that time of year again. When death rains upon us and the terrors of the all-nighters emerge from the hell hole we call midterms at McMaster","H.G. Thode Library of Science and Engineering","Engineering","library.mcmaster.ca",(float)43.261395, (float)-79.922434);

  public void makeEvents() {
      List<Event> eventList = new ArrayList<>();
      eventList.add(event1);
      eventList.add(event2);
      eventList.add(event3);
  }


}
