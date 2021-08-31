package com.intuit.model;



    public class ResponseTo {

    private Employee emp;
    private int requestId;
    private String request;
    private String status;

        public Employee getEmp() {
            return emp;
        }

        public void setEmp(Employee emp) {
            this.emp = emp;
        }

        public int getRequestId() {
            return requestId;
        }

        public void setRequestId(int requestId) {
            this.requestId = requestId;
        }

        public String getRequest() {
            return request;
        }

        public void setRequest(String request) {
            this.request = request;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "ResponseTo{" +
                    "emp=" + emp.toString() +
                    ", requestId=" + requestId +
                    ", request='" + request + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
