/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
          auto cmp = [](ListNode* a, ListNode* b) {
        return a->val > b->val;   // Min-heap
    };

    priority_queue<ListNode*, vector<ListNode*>, decltype(cmp)> pq(cmp);

    // Push the head of each non-empty list
    for (ListNode* node : lists) {
        if (node) {
            pq.push(node);
        }
    }

    ListNode dummy(0);
    ListNode* tail = &dummy;

    while (!pq.empty()) {
        ListNode* curr = pq.top();
        pq.pop();

        tail->next = curr;
        tail = tail->next;

        if (curr->next) {
            pq.push(curr->next);
        }
    }

    return dummy.next;
    }
};