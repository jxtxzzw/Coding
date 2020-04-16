int linkedListCircleLength(NODE* head) {
    NODE* walker = head;
    NODE* runner = head;
    while(runner !=NULL && runner->next != NULL)
    {
        walker = walker->next;
        runner = runner->next->next;
        if(walker == runner)
            break;
    }
    int len = 0;
    while(runner != NULL && runner->next != NULL)
    {
        ++len;
        walker = walker->next;
        runner = runner->next->next;
        if(walker == runner)
            break;
    }
    return len;
}
