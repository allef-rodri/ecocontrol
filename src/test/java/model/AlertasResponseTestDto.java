package model;

import java.util.List;

public class AlertasResponseTestDto {

        private List<AlertaTestDto> content;
        private int totalPages;
        private long totalElements;
        private boolean last;
        private int size;
        private int number;
        private boolean first;
        private boolean empty;

        // GETTERS
        public List<AlertaTestDto> getContent() {
                return content;
        }

        public int getTotalPages() {
                return totalPages;
        }

        public long getTotalElements() {
                return totalElements;
        }

        public boolean isLast() {
                return last;
        }

        public int getSize() {
                return size;
        }

        public int getNumber() {
                return number;
        }

        public boolean isFirst() {
                return first;
        }

        public boolean isEmpty() {
                return empty;
        }

        // SETTERS
        public void setContent(List<AlertaTestDto> content) {
                this.content = content;
        }

        public void setTotalPages(int totalPages) {
                this.totalPages = totalPages;
        }

        public void setTotalElements(long totalElements) {
                this.totalElements = totalElements;
        }

        public void setLast(boolean last) {
                this.last = last;
        }

        public void setSize(int size) {
                this.size = size;
        }

        public void setNumber(int number) {
                this.number = number;
        }

        public void setFirst(boolean first) {
                this.first = first;
        }

        public void setEmpty(boolean empty) {
                this.empty = empty;
        }
}
