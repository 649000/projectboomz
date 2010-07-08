package app

class CounterService {

    static transactional = true

    def counter=0;

    def serviceMethod() {

    }

    def _Counter()
    {
        counter++
        return counter
    }

    def CounterValue()
    {
        return counter
    }
}
