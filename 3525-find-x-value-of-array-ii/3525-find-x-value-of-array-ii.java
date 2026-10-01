class Solution {
    int n,k;
    int[] product;
    long[][] count;

    void build(int node,int l,int r,int[] nums){
        if(l==r){
            product[node]=nums[l]%k;
            count[node][product[node]]=1;
            return;
        }

        int mid=(l+r)/2;
        build(node*2,l,mid,nums);
        build(node*2+1,mid+1,r,nums);
        merge(node,node*2,node*2+1);
    }

    void merge(int node,int left,int right){
        product[node]=(product[left]*product[right])%k;

        for(int i=0;i<k;i++){
            count[node][i]=count[left][i];
        }

        for(int j=0;j<k;j++){
            int rem=(product[left]*j)%k;
            count[node][rem]+=count[right][j];
        }
    }

    void update(int node,int l,int r,int index,int value){
        if(l==r){
            product[node]=value%k;
            for(int i=0;i<k;i++) count[node][i]=0;
            count[node][product[node]]=1;
            return;
        }

        int mid=(l+r)/2;

        if(index<=mid)
            update(node*2,l,mid,index,value);
        else
            update(node*2+1,mid+1,r,index,value);

        merge(node,node*2,node*2+1);
    }

    Node query(int node,int l,int r,int ql,int qr){
        if(ql<=l&&r<=qr){
            Node res=new Node(k);
            res.product=product[node];

            for(int i=0;i<k;i++)
                res.count[i]=count[node][i];

            return res;
        }

        int mid=(l+r)/2;

        if(qr<=mid)
            return query(node*2,l,mid,ql,qr);

        if(ql>mid)
            return query(node*2+1,mid+1,r,ql,qr);

        Node left=query(node*2,l,mid,ql,qr);
        Node right=query(node*2+1,mid+1,r,ql,qr);

        Node res=new Node(k);
        res.product=(left.product*right.product)%k;

        for(int i=0;i<k;i++)
            res.count[i]=left.count[i];

        for(int j=0;j<k;j++){
            int rem=(left.product*j)%k;
            res.count[rem]+=right.count[j];
        }

        return res;
    }

    static class Node{
        int product;
        long[] count;

        Node(int k){
            count=new long[k];
        }
    }

    public int[] resultArray(int[] nums,int k,int[][] queries){
        this.n=nums.length;
        this.k=k;

        product=new int[4*n];
        count=new long[4*n][k];

        build(1,0,n-1,nums);

        int[] result=new int[queries.length];

        for(int i=0;i<queries.length;i++){
            int index=queries[i][0];
            int value=queries[i][1];
            int start=queries[i][2];
            int x=queries[i][3];

            update(1,0,n-1,index,value);

            Node res=query(1,0,n-1,start,n-1);

            result[i]=(int)res.count[x];
        }

        return result;
    }
}