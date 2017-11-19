package ahmed.sabih.com.astroassignment.models;

import java.util.List;

/**
 * Created by sabih on 04-Nov-17.
 * ahmed.engr90@gmail.com
 */

public class EventsResponse {
    public String responseCode;
    public String responseMessage;
    public List<Getevent> getevent;

    public List<Getevent> getGetevent() {
        return getevent;
    }

    public class Getevent {
        public String eventID;
        public int channelId;
        public String channelStbNumber;
        public String channelHD;
        public String channelTitle;
        public String epgEventImage;
        public String certification;
        public String displayDateTimeUtc;
        public String displayDateTime;
        public String displayDuration;
        public String siTrafficKey;
        public String programmeTitle;
        public String programmeId;
        public String episodeId;
        public String shortSynopsis;
        public String longSynopsis;
        public String actors;
        public String directors;
        public String producers;
        public String genre;
        public String subGenre;
        public boolean live;
        public boolean premier;
        public boolean ottBlackout;
        public String highlight;
        public String contentId;
        public List<ContentImage> contentImage;
        public String groupKey;
        public List<Object> vernacularData;

        public String getEventID() {
            return eventID;
        }

        public int getChannelId() {
            return channelId;
        }

        public String getChannelStbNumber() {
            return channelStbNumber;
        }

        public String getChannelHD() {
            return channelHD;
        }

        public String getChannelTitle() {
            return channelTitle;
        }

        public String getEpgEventImage() {
            return epgEventImage;
        }

        public String getCertification() {
            return certification;
        }

        public String getDisplayDateTimeUtc() {
            return displayDateTimeUtc;
        }

        public String getDisplayDateTime() {
            return displayDateTime;
        }

        public String getDisplayDuration() {
            return displayDuration;
        }

        public String getSiTrafficKey() {
            return siTrafficKey;
        }

        public String getProgrammeTitle() {
            return programmeTitle;
        }

        public String getProgrammeId() {
            return programmeId;
        }

        public String getEpisodeId() {
            return episodeId;
        }

        public String getShortSynopsis() {
            return shortSynopsis;
        }

        public String getLongSynopsis() {
            return longSynopsis;
        }

        public String getActors() {
            return actors;
        }

        public String getDirectors() {
            return directors;
        }

        public String getProducers() {
            return producers;
        }

        public String getGenre() {
            return genre;
        }

        public String getSubGenre() {
            return subGenre;
        }

        public boolean isLive() {
            return live;
        }

        public boolean isPremier() {
            return premier;
        }

        public boolean isOttBlackout() {
            return ottBlackout;
        }

        public String getHighlight() {
            return highlight;
        }

        public String getContentId() {
            return contentId;
        }

        public List<ContentImage> getContentImage() {
            return contentImage;
        }

        public String getGroupKey() {
            return groupKey;
        }

        public List<Object> getVernacularData() {
            return vernacularData;
        }

        private class ContentImage {
            public String imageURL;
            public String imageRole;

            public String getImageURL() {
                return imageURL;
            }

            public String getImageRole() {
                return imageRole;
            }
        }
    }
}
