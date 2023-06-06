package com.example.demo.entity.cashing;

public enum CashedId {

    popular(5, 0),
    topFive(10, 10);

    private final int count;
    private final long ttl;

    CashedId(int count, long ttl) {
        this.count = count;
        this.ttl = ttl;
    }

    public int getCount() {
        return count;
    }

    public long getTtl() {
        return ttl;
    }

}
