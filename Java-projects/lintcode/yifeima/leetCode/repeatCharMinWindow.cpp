#include <iostream>
#include <cstring>

using namespace std;

void f(char* S, char* T){
        int set_T[26]={0};
        int Slen=strlen(S), Tlen=strlen(T);
        int count_b=0, count=0;

        //initialize SET_T:
        for(int i=0; i<Tlen; i++){
                if(set_T[tolower(T[i])-'a']==0){
                        count_b++;
                        set_T[tolower(T[i])-'a']=1;
                }
        }

        if(count_b==0) cout<<"NULL";

        int l=0, r;
        int ll=-1, rr=Slen, minLen=rr-ll;

        for(r=0; r<Slen; r++){
                int idx=tolower(S[r])-'a';
                if(set_T[idx]){
                        count++;
                        set_T[idx]++;
                }

                if(count>=count_b){ //the current window is ready, begin to shrink.
                        while(l<r){
                                idx=tolower(S[l])-'a';
                                if(set_T[idx]==2) break;
                                if(set_T[idx]>2) set_T[idx]--;
                                l++;
                        }

                        if(minLen>r-l+1) {
                                minLen=r-l+1;
                                ll=l;
                                rr=r;
                        }

                        //continue to the next window:
                        set_T[idx]--;
                        l++;
                        count--;
                }
        }
        if(ll==-1) cout<<"NULL";
        else{
                cout<<" The length of the substring in a that includes b is: "<<minLen<<endl
                        <<" The substring is: ";
                for (int i=ll; i<=rr; i++)
                        cout<<S[i];
                cout<<endl;
        }
}